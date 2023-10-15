package com.acikek.predicate.api.impl.serializer;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.acikek.predicate.api.util.EnumPredicate;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.JsonHelper;
import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@ApiStatus.Internal
public record EnumPredicateSerializer<E extends Enum<E>>(
        Class<E> type) implements RegularPredicateSerializer<EnumPredicate<E>> {

    private E fromString(String string) {
        var instance = EnumUtils.getEnumIgnoreCase(type, string);
        if (instance == null) {
            throw new JsonSyntaxException("'" + string + "' is not a valid " + type);
        }
        return instance;
    }

    private String toString(E instance) {
        return instance.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public EnumPredicate<E> fromJson(JsonElement json) {
        var string = JsonHelper.asString(json, "enum instance");
        return new EnumPredicate<>(this, fromString(string));
    }

    @Override
    public JsonElement toJson(@NotNull EnumPredicate<E> instance) {
        return new JsonPrimitive(toString(instance.instance()));
    }

    @Override
    public EnumPredicate<E> read(PacketByteBuf buf) {
        var string = buf.readString();
        return new EnumPredicate<>(this, fromString(string));
    }

    @Override
    public void write(PacketByteBuf buf, @NotNull EnumPredicate<E> instance) {
        buf.writeString(toString(instance.instance()));
    }
}
