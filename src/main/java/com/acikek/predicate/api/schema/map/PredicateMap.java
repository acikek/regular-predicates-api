package com.acikek.predicate.api.schema.map;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.impl.map.PredicateMapImpl;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * WAIT... TODO.. THIS CAN BE A REGULAR PREDICATE...
 */
public interface PredicateMap {

    boolean test(String name, Object value);

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

    static PredicateMap of(ImmutableMap<String, RegularPredicate<?>> predicates) {
        return new PredicateMapImpl(predicates);
    }
}
