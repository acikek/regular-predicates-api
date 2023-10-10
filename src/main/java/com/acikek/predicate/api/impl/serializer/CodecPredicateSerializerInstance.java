package com.acikek.predicate.api.impl.serializer;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.serializer.CodecPredicateSerializer;
import com.mojang.serialization.Codec;

public record CodecPredicateSerializerInstance<P extends RegularPredicate<?>>(
        Codec<P> codec) implements CodecPredicateSerializer<P> {
}
