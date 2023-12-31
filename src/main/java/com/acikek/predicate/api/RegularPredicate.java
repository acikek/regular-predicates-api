package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * A boolean-valued function of one argument that can be freely serialized or deserialized
 * via a {@link RegularPredicateSerializer}.
 * <p>
 * Most regular predicates should be implemented as a {@link FriendlyPredicate}.
 * Note that the {@code rp$} prefix is used to prevent method name clashes with vanilla predicates.
 * <p>
 * For utilities relating to regular predicates, see {@link RegularPredicatesAPI}.
 *
 * @param <T> the type of the input to the predicate
 * @see Predicate
 */
public interface RegularPredicate<T> {

    /**
     * @return the class of the input type
     */
    default @NotNull Class<T> rp$contextType() {
        throw new AssertionError();
    }

    /**
     * @return the corresponding predicate serializer
     * @see RegularPredicates
     */
    default @NotNull RegularPredicateSerializer<?> rp$serializer() {
        throw new AssertionError();
    }

    // TODO: this would be really useful for data criteria but need to figure out a justification otherwise and/or network stuff
    default T rp$deserializeValue(JsonElement element) {
        return null;
    }

    /**
     * Evaluates the predicate on the given argument.
     * @return {@code true} if the argument matches the predicate, otherwise {@code false}
     */
    default boolean rp$test(T t) {
        throw new AssertionError();
    }
}
