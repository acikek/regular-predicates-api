package com.acikek.predicate.api.serializer;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.RegularPredicate;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link Codec}-backed predicate serializer. Specifically, the supplied codec should be able to
 * serialize and deserialize the relevant predicate type.
 */
public interface CodecPredicateSerializer<P extends RegularPredicate<?>> extends RegularPredicateSerializer<P> {

    /**
     * @return the codec to use for serialization
     */
    Codec<P> codec();

    @Override
    default P fromJson(JsonElement json) {
        return codec().decode(JsonOps.INSTANCE, json)
                .getOrThrow(false, RegularPredicatesMod.LOGGER::error)
                .getFirst();
    }

    @Override
    default JsonElement toJson(@NotNull P instance) {
        return codec().encodeStart(JsonOps.INSTANCE, instance)
                .getOrThrow(false, RegularPredicatesMod.LOGGER::error);
    }

    @Override
    default P read(PacketByteBuf buf) {
        return buf.decodeAsJson(codec());
    }

    @Override
    default void write(PacketByteBuf buf, @NotNull P instance) {
        buf.encodeAsJson(codec(), instance);
    }
}
