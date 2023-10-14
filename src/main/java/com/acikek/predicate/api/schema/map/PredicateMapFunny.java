package com.acikek.predicate.api.schema.map;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.schema.PredicateSchema;
import com.acikek.predicate.api.serializer.PredicateSerializers;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

@SuppressWarnings("unchecked")
public class PredicateMapFunny implements FriendlyPredicate<Map<String, Object>> {

    private final PredicateSchema schema;
    private final ImmutableMap<String, RegularPredicate<?>> predicates;
    private final RegularPredicateSerializer<PredicateMapFunny> serializer;

    public PredicateMapFunny(PredicateSchema schema, ImmutableMap<String, RegularPredicate<?>> predicates) {
        this.schema = schema;
        this.predicates = predicates;
        serializer = PredicateSerializers.delegated(schema::deserialize, PredicateMapFunny::toJson);
    }

    public PredicateMapFunny(ImmutableMap<String, RegularPredicate<?>> predicates) {
        this(PredicateSchema.fromMap(predicates), predicates);
    }

    @Override
    public Class<Map<String, Object>> contextType() {
        return (Class<Map<String, Object>>) (Class<?>) Map.class;
    }

    @Override
    public RegularPredicateSerializer<?> serializer() {
        return serializer;
    }

    public PredicateSchema schema() {
        return schema;
    }

    public ImmutableMap<String, RegularPredicate<?>> predicates() {
        return predicates;
    }

    @Override
    public boolean test(Map<String, Object> stringObjectMap) {
        return false;
    }

    public JsonElement toJson() {
        JsonObject obj = new JsonObject();
        for (var entry : predicates.entrySet()) {
            obj.add(entry.getKey(), RegularPredicates.toJson(entry.getValue()));
        }
        return obj;
    }
}
