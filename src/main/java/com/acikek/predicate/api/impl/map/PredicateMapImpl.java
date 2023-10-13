package com.acikek.predicate.api.impl.map;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.map.PredicateMap;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Objects;

public record PredicateMapImpl(ImmutableMap<String, RegularPredicate<?>> predicates) implements PredicateMap {

    private static <T> boolean test(String name, Object value, RegularPredicate<T> predicate) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(predicate.rp$contextType());
        try {
            return RegularPredicates.test(predicate, value);
        }
        catch (ClassCastException exception) {
            throw new IllegalStateException("failed to test predicate '" + name + "'", exception);
        }
    }

    @Override
    public boolean test(String name, Object value) {
        return test(name, value, predicates.get(name));
    }

    @Override
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
}
