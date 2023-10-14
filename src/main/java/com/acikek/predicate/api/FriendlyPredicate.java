package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public interface FriendlyPredicate<T> extends RegularPredicate<T>, Predicate<T> {

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
}