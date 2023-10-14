package com.acikek.predicate.api.schema;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.schema.map.PredicateMap;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public record PredicateSchema(Collection<SchemaElement> elements) implements FriendlyPredicate<PredicateMap> {

    @Override
    public Class<PredicateMap> contextType() {
        return PredicateMap.class;
    }

    @Override
    public RegularPredicateSerializer<?> serializer() {
        return RegularPredicates.SCHEMA;
    }

    @Override
    public boolean test(PredicateMap map) {
        return SchemaElement.test(elements, map);
    }

    public static PredicateSchema fromJson(JsonElement element) {
        var array = JsonHelper.asArray(element, "schema");
        List<SchemaElement> elements = new ArrayList<>();
        for (var subElement : array) {
            var obj = JsonHelper.asObject(subElement, "element object");
            elements.add(SchemaElement.fromJson(obj));
        }
        return new PredicateSchema(elements);
    }

    public static PredicateSchema fromMap(Map<String, RegularPredicate<?>> map) {
        return new PredicateSchema(SchemaElement.fromMap(map));
    }

    public JsonElement toJson() {
        var array = new JsonArray();
        for (var element : elements) {
            array.add(element.toJson());
        }
        return array;
    }

    public PredicateMap deserialize(JsonElement json) {
        var obj = JsonHelper.asObject(json, "predicate map");
        ImmutableMap.Builder<String, RegularPredicate<?>> builder = new ImmutableMap.Builder<>();
        for (var element : elements) {
            var predicateJson = obj.get(element.name());
            if (predicateJson == null) {
                // TODO: this is probably unnecessary/something i need to figure out
                // TODO: would also need to apply these constraints to predicate map creation?
                throw new JsonSyntaxException("missing required element '" + element.name() + "' in predicate map");
            }
            builder.put(element.name(), element.deserialize(predicateJson));
        }
        return new PredicateMap(this, builder.build());
    }
}
