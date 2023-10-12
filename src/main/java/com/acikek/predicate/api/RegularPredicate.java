package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;

import java.util.function.Predicate;

// TODO: reimplement predicate class with prefixes?
// TODO: RegularPredicateContainer for mixin'd classes to implement?
public interface RegularPredicate<T> extends Predicate<T> {

    default Class<T> rp$contextType() {
        return null;
    }

    default RegularPredicateSerializer<?> rp$serializer() {
        return null;
    }

    @Override
    default boolean test(T t) {
        return false;
    }

    default boolean rp$test(T t) {
        return test(t);
    }
}
