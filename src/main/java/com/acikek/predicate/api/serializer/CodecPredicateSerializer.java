package com.acikek.predicate.api.serializer;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.RegularPredicate;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;

public interface CodecPredicateSerializer<P extends RegularPredicate<?>> extends RegularPredicateSerializer<P> {

    Codec<P> codec();

    @Override
    default P fromJson(JsonElement json) {
        return codec().decode(JsonOps.INSTANCE, json)
                .getOrThrow(false, RegularPredicatesMod.LOGGER::error)
                .getFirst();
    }

    @Override
    default JsonElement toJson(P instance) {
        return codec().encodeStart(JsonOps.INSTANCE, instance)
                .getOrThrow(false, RegularPredicatesMod.LOGGER::error);
    }

    @Override
    default P read(PacketByteBuf buf) {
        return buf.decodeAsJson(codec());
    }

    @Override
    default void write(PacketByteBuf buf, P instance) {
        buf.encodeAsJson(codec(), instance);
    }
}
