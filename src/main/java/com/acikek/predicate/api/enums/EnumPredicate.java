package com.acikek.predicate.api.enums;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;

/**
 * A predicate that tests equality against a given enum constant.<br>
 * Create an enum predicate instance with {@link EnumPredicateSerializer#create(Enum)}.
 * @param <E> the enum type
 */
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
