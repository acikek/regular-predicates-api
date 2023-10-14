package com.acikek.predicate.api.schema;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.schema.map.PredicateMapFunny;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.SurfaceThresholdFilterPlacementModifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;

// Needs a *private* constructor, otherwise an interface or record would work.
public class SchemaElement {

    /*public static final Codec<SchemaElement> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(SchemaElement::name),
                    Identifier.CODEC.optionalFieldOf("type").forGetter(element -> Optional.ofNullable(element.typeId())),
                    Codec.
            ).apply(instance, SchemaElement::new)
    );*/

    private final String name;
    private final RegularPredicateSerializer<?> type;
    private final Collection<SchemaElement> children;

    private SchemaElement(String name, RegularPredicateSerializer<?> type, Collection<SchemaElement> children) {
        this.name = name;
        this.type = type;
        this.children = children;
        SurfaceThresholdFilterPlacementModifier.CODEC
    }

    // Neat, these methods call each other
    public static boolean test(Collection<SchemaElement> elements, PredicateMapFunny map) {
        for (var element : elements) {
            var predicate = map.predicates().get(element.name());
            if (predicate == null || !element.test(predicate)) {
                return false;
            }
        }
        return true;
    }

    public boolean test(RegularPredicate<?> predicate) {
        if (predicate instanceof PredicateMapFunny innerMap && !children.isEmpty()) {
            return test(children, innerMap);
        }
        return predicate.rp$serializer() == type;
    }

    public String name() {
        return name;
    }

    public @Nullable RegularPredicateSerializer<?> type() {
        return type;
    }

    public @Nullable Identifier typeId() {
        if (type == null) {
            return null;
        }
        return RegularPredicates.REGISTRY.getId(type);
    }

    public Collection<SchemaElement> children() {
        return children;
    }
}
