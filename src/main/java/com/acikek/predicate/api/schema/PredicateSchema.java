package com.acikek.predicate.api.schema;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.schema.map.PredicateMapFunny;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record PredicateSchema(Collection<SchemaElement> elements) implements FriendlyPredicate<PredicateMapFunny> {

    @Override
    public Class<PredicateMapFunny> contextType() {
        return PredicateMapFunny.class;
    }

    @Override
    public RegularPredicateSerializer<?> serializer() {
        return null;
    }

    @Override
    public boolean test(PredicateMapFunny map) {
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

    public JsonElement toJson() {
        var array = new JsonArray();
        for (var element : elements) {
            array.add(element.toJson());
        }
        return array;
    }
}
