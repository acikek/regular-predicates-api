package com.acikek.predicate.api.impl.serializer;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public record DelegatedPredicateSerializer<P extends RegularPredicate<?>>
        (Function<JsonElement, P> deserializer, Function<P, JsonElement> serializer)
        implements RegularPredicateSerializer<P> {

    private static final Gson GSON = new Gson();

    @Override
    public P fromJson(JsonElement json) {
        return deserializer.apply(json);
    }

    @Override
    public JsonElement toJson(@NotNull P instance) {
        return serializer.apply(instance);
    }

    @Override
    public P read(PacketByteBuf buf) {
        var json = JsonHelper.deserialize(GSON, buf.readString(), JsonElement.class);
        return deserializer.apply(json);
    }

    @Override
    public void write(PacketByteBuf buf, @NotNull P instance) {
        var json = GSON.toJson(serializer.apply(instance));
        buf.writeString(json);
    }
}
