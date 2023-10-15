package com.acikek.predicate.api;

import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.JsonElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

@SuppressWarnings("unchecked")
public class RegularPredicatesAPI {

    public static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> serializer(P predicate) {
        return (RegularPredicateSerializer<P>) predicate.rp$serializer();
    }

    public static <P extends RegularPredicate<?>> JsonElement toJson(P predicate) {
        return serializer(predicate).toJson(predicate);
    }

    public static <P extends RegularPredicate<?>> void write(PacketByteBuf buf, P predicate) {
        serializer(predicate).write(buf, predicate);
    }

    public static <T, P extends RegularPredicate<T>> boolean test(P predicate, T value) {
        return predicate.rp$test(value);
    }

    public static <T, P extends RegularPredicate<T>> boolean tryTest(P predicate, Object value) throws ClassCastException {
        T input = predicate.rp$contextType().cast(value);
        return predicate.rp$test(input);
    }

    public static Identifier getId(RegularPredicate<?> predicate) {
        return RegularPredicates.REGISTRY.getId(predicate.rp$serializer());
    }
}
