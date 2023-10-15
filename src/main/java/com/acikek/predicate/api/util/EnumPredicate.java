package com.acikek.predicate.api.util;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;

@SuppressWarnings("unchecked")
public record EnumPredicate<E extends Enum<E>>(RegularPredicateSerializer<EnumPredicate<E>> serializer, E instance) implements FriendlyPredicate<E> {

    @Override
    public Class<E> contextType() {
        return (Class<E>) (Class<?>) Enum.class;
    }

    @Override
    public boolean test(E instance) {
        return this.instance == instance;
    }
}
