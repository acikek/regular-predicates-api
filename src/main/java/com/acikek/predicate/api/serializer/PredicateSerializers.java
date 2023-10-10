package com.acikek.predicate.api.serializer;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.impl.serializer.CodecPredicateSerializerInstance;
import com.acikek.predicate.api.impl.serializer.DelegatedPredicateSerializer;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;

import java.util.function.Function;

public class PredicateSerializers {

    public static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> codec(Codec<P> codec) {
        return new CodecPredicateSerializerInstance<>(codec);
    }

    public static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> delegated(Function<JsonElement, P> deserializer, Function<P, JsonElement> serializer) {
        return new DelegatedPredicateSerializer<>(deserializer, serializer);
    }
}
