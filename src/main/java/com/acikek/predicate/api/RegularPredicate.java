package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;

import java.util.function.Predicate;

public interface RegularPredicate<T> extends Predicate<T> {

    default Class<T> contextType() {
        return null;
    }

    default RegularPredicateSerializer<? extends RegularPredicate<T>> serializer() {
        return null;
    }
}
