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

    // TODO: make use of this idea...
    /*interface Friendly<T> extends RegularPredicate<T>, Predicate<T> {

        Class<T> contextType();

        RegularPredicateSerializer<?> serializer();

        @Override
        @NotNull
        default Class<T> rp$contextType() {
            return contextType();
        }

        @Override
        @NotNull
        default RegularPredicateSerializer<?> rp$serializer() {
            return serializer();
        }

        @Override
        default boolean rp$test(T t) {
            return test(t);
        }
    }*/
}
