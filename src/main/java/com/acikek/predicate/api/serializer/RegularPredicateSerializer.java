package com.acikek.predicate.api.serializer;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.impl.serializer.CodecPredicateSerializerInstance;
import com.acikek.predicate.api.impl.serializer.DelegatedPredicateSerializer;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import net.minecraft.network.PacketByteBuf;

import java.util.function.Function;

public interface RegularPredicateSerializer<P extends RegularPredicate<?>> {

    P fromJson(JsonElement json);

    JsonElement toJson(P instance);

    P read(PacketByteBuf buf);

    void write(PacketByteBuf buf, P instance);

    static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> codec(Codec<P> codec) {
        return new CodecPredicateSerializerInstance<>(codec);
    }

    static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> delegated(Function<JsonElement, P> deserializer, Function<P, JsonElement> serializer) {
        return new DelegatedPredicateSerializer<>(deserializer, serializer);
    }
}
