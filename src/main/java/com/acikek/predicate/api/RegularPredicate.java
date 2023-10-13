package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;

public interface RegularPredicate<T> {

    default Class<T> rp$contextType() {
        throw new AssertionError();
    }

    default RegularPredicateSerializer<?> rp$serializer() {
        throw new AssertionError();
    }

    default boolean rp$test(T t) {
        throw new AssertionError();
    }
}
