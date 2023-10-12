package com.acikek.predicate.api.map;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface PredicateMap {

    boolean test(String name, Object value);

    // TODO: up to implementation?
    default boolean test(Map<String, Object> map) {
        for (var entry : map.entrySet()) {
            if (!test(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    boolean test(List<Object> values);

    default boolean test(Object... values) {
        return test(Arrays.stream(values).toList());
    }
}
