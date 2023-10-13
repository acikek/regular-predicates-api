package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;

import java.util.function.Predicate;

public interface RegularPredicate<T> extends Predicate<T> {

    default Class<T> rp$contextType() {
        throw new AssertionError();
    }

    default RegularPredicateSerializer<?> rp$serializer() {
        throw new AssertionError();
    }

    @Override
    default boolean test(T t) {
        throw new AssertionError();
    }
}
