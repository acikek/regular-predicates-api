package com.acikek.predicate.api;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.impl.serializer.DelegatedPredicateSerializer;
import com.acikek.predicate.api.serializer.PredicateSerializers;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.nbt.NbtElement;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.State;

@SuppressWarnings("unchecked")
public class RegularPredicates {

    public static final RegistryKey<Registry<RegularPredicateSerializer<?>>> REGISTRY_KEY = RegistryKey.ofRegistry(RegularPredicatesMod.id("predicate"));
    public static final Registry<RegularPredicateSerializer<?>> REGISTRY = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final RegularPredicateSerializer<? extends RegularPredicate<NbtElement>> NBT = PredicateSerializers.<RegularPredicate<NbtElement>>delegated(
            NbtPredicate::fromJson,
            nbt -> ((NbtPredicate) nbt).toJson()
    );

    public static final RegularPredicateSerializer<? extends RegularPredicate<State<?, ?>>> STATE = PredicateSerializers.delegated(
            json -> (RegularPredicate<State<?, ?>>) StatePredicate.fromJson(json),
            state -> ((StatePredicate) state).toJson()
    );
}
