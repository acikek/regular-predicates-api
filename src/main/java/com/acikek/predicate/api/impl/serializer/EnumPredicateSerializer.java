package com.acikek.predicate.api.impl.serializer;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.acikek.predicate.api.util.EnumPredicate;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.JsonHelper;
import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public record EnumPredicateSerializer<E extends Enum<E>>(
        Class<E> type) implements RegularPredicateSerializer<EnumPredicate<E>> {

    @Override
    public EnumPredicate<E> fromJson(JsonElement json) {
        var string = JsonHelper.asString(json, "enum instance");
        var instance = EnumUtils.getEnumIgnoreCase(type, string);
        if (instance == null) {
            throw new JsonSyntaxException("'" + string + "' is not a valid " + type);
        }
        return new EnumPredicate<>(this, instance);
    }

    @Override
    public JsonElement toJson(@NotNull EnumPredicate<E> instance) {
        return new JsonPrimitive(instance.instance().name().toLowerCase(Locale.ROOT));
    }

    @Override
    public EnumPredicate<E> read(PacketByteBuf buf) {
        return new EnumPredicate<>(this, buf.readEnumConstant(type));
    }

    @Override
    public void write(PacketByteBuf buf, @NotNull EnumPredicate<E> instance) {
        buf.writeEnumConstant(instance.instance());
    }
}
