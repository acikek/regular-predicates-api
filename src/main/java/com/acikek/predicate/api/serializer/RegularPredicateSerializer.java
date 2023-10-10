package com.acikek.predicate.api.serializer;

import com.acikek.predicate.api.RegularPredicate;
import com.google.gson.JsonElement;
import net.minecraft.network.PacketByteBuf;

public interface RegularPredicateSerializer<P extends RegularPredicate<?>> {

    P fromJson(JsonElement json);

    JsonElement toJson(P instance);

    P read(PacketByteBuf buf);

    void write(PacketByteBuf buf, P instance);
}
