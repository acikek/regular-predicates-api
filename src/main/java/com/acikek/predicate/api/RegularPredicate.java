package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import org.jetbrains.annotations.NotNull;

public interface RegularPredicate<T> {

    default @NotNull Class<T> rp$contextType() {
        throw new AssertionError();
    }

    default @NotNull RegularPredicateSerializer<?> rp$serializer() {
        throw new AssertionError();
    }

    default boolean rp$test(T t) {
        throw new AssertionError();
    }
}
