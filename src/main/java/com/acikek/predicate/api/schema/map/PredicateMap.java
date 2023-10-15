package com.acikek.predicate.api.schema.map;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicatesAPI;
import com.acikek.predicate.api.schema.PredicateSchema;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class PredicateMap implements FriendlyPredicate<Map<String, Object>> {

    private final PredicateSchema schema;
    private final ImmutableMap<String, RegularPredicate<?>> predicates;
    private final RegularPredicateSerializer<PredicateMap> serializer;

    public PredicateMap(PredicateSchema schema, ImmutableMap<String, RegularPredicate<?>> predicates) {
        this.schema = schema;
        this.predicates = predicates;
        serializer = RegularPredicateSerializer.delegated(schema::deserialize, PredicateMap::toJson);
    }

    public PredicateMap(ImmutableMap<String, RegularPredicate<?>> predicates) {
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

    public boolean test(String name, Object value) {
        return test(name, value, predicates.get(name));
    }

    public boolean test(List<Object> values) {
        var predicateList = predicates.entrySet().stream().toList();
        for (int i = 0; i < values.size(); i++) {
            if (i >= predicateList.size()) {
                break;
            }
            var entry = predicateList.get(i);
            if (!test(entry.getKey(), values.get(i), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public boolean test(Object... values) {
        return test(Arrays.stream(values).toList());
    }

    @Override
    public boolean test(Map<String, Object> map) {
        for (var entry : map.entrySet()) {
            if (!test(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public JsonElement toJson() {
        JsonObject obj = new JsonObject();
        for (var entry : predicates.entrySet()) {
            obj.add(entry.getKey(), RegularPredicatesAPI.toJson(entry.getValue()));
        }
        return obj;
    }

    // TODO switch these to debugs :P
    // TODO yeah make these debug logs but like lock them behind a bool or smth cuz serializing every time u want to test anything? no
    private static <T> boolean test(String name, Object value, RegularPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(predicate.rp$contextType());
        RegularPredicatesMod.LOGGER.info("Testing '{}'...", name);
        RegularPredicatesMod.LOGGER.info("- Predicate: '{}', {}", RegularPredicatesAPI.getId(predicate), RegularPredicatesAPI.toJson(predicate));
        RegularPredicatesMod.LOGGER.info("- Input: {} (should be {})", value, predicate.rp$contextType());
        try {
            boolean result = RegularPredicatesAPI.tryTest(predicate, value);
            RegularPredicatesMod.LOGGER.info("- Result: {}", result);
            return result;
        }
        catch (ClassCastException exception) {
            throw new IllegalStateException("failed to test predicate '" + name + "'", exception);
        }
    }
}
