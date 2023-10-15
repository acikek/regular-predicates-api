package com.acikek.predicate.api.serializer;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.serializer.CodecPredicateSerializerInstance;
import com.acikek.predicate.api.impl.serializer.DelegatedPredicateSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.mojang.serialization.Codec;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A serializer that can take instances of a {@link RegularPredicate} and convert them to
 * JSON or write them to a buffer, or vice versa.
 * <p>
 * In almost all cases, serializer implementations should be singletons and registered to
 * {@link RegularPredicates#REGISTRY}.
 *
 * @param <P> the type of the predicate
 * @see CodecPredicateSerializer
 * @see RegularPredicates
 * @see RegularPredicateSerializer#codec(Codec)
 * @see RegularPredicateSerializer#delegated(Function, Function)
 */
public interface RegularPredicateSerializer<P extends RegularPredicate<?>> {

    /**
     * @param json <b>any</b> JSON element. May be {@code null} or {@link JsonNull#INSTANCE}!
     * @return a deserialized predicate instance
     */
    P fromJson(@Nullable JsonElement json);

    /**
     * @param instance a predicate instance
     * @return a serialized predicate JSON element. May be {@code null} or {@link JsonNull#INSTANCE}.
     */
    @Nullable JsonElement toJson(@NotNull P instance);

    /**
     * @param buf the byte buffer to read from
     * @return a deserialized predicate instance
     */
    P read(PacketByteBuf buf);

    /**
     * @param buf the byte buffer to write to
     * @param instance a predicate instance
     */
    void write(PacketByteBuf buf, @NotNull P instance);

    /**
     * @return a codec-backed predicate serializer using the specified codec
     * @see CodecPredicateSerializer
     */
    static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> codec(Codec<P> codec) {
        return new CodecPredicateSerializerInstance<>(codec);
    }

    /**
     * @return a predicate serializer using the specified functions
     * for both JSON serialization and network encoding
     */
    static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> delegated(Function<JsonElement, P> deserializer, Function<P, JsonElement> serializer) {
        return new DelegatedPredicateSerializer<>(deserializer, serializer);
    }
}
