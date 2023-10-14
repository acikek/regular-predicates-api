package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * A {@link RegularPredicate} with non-prefixed methods and a {@link Predicate} extension.
 */
public interface FriendlyPredicate<T> extends RegularPredicate<T>, Predicate<T> {

    /**
     * @see RegularPredicate#rp$contextType()
     */
    Class<T> contextType();

    /**
     * @see RegularPredicate#rp$serializer()
     */
    RegularPredicateSerializer<?> serializer();

    @Override
    @ApiStatus.NonExtendable
    default @NotNull Class<T> rp$contextType() {
        return contextType();
    }

    @Override
    @ApiStatus.NonExtendable
    default @NotNull RegularPredicateSerializer<?> rp$serializer() {
        return serializer();
    }

    @Override
    @ApiStatus.NonExtendable
    default boolean rp$test(T t) {
        return test(t);
    }
}