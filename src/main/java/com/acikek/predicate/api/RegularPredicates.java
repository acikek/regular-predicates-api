package com.acikek.predicate.api;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.serializer.PredicateSerializers;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

@SuppressWarnings("unchecked")
public class RegularPredicates {

    public static final RegistryKey<Registry<RegularPredicateSerializer<?>>> REGISTRY_KEY = RegistryKey.ofRegistry(RegularPredicatesMod.id("predicate"));
    public static final Registry<RegularPredicateSerializer<?>> REGISTRY = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final RegularPredicateSerializer<NbtPredicate> NBT = PredicateSerializers.delegated(NbtPredicate::fromJson, NbtPredicate::toJson);
    public static final RegularPredicateSerializer<StatePredicate> STATE = PredicateSerializers.delegated(StatePredicate::fromJson, StatePredicate::toJson);


    public static <P extends RegularPredicate<?>> RegularPredicateSerializer<P> serializer(P predicate) {
        return (RegularPredicateSerializer<P>) predicate.rp$serializer();
    }

    public static <P extends RegularPredicate<?>> JsonElement toJson(P predicate) {
        return serializer(predicate).toJson(predicate);
    }

    public static <P extends RegularPredicate<?>> void write(PacketByteBuf buf, P predicate) {
        serializer(predicate).write(buf, predicate);
    }
}
