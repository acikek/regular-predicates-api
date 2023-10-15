package com.acikek.predicate.api;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.schema.PredicateSchema;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

public class RegularPredicates {

    public static final RegistryKey<Registry<RegularPredicateSerializer<?>>> REGISTRY_KEY = RegistryKey.ofRegistry(RegularPredicatesMod.id("predicate"));
    public static final Registry<RegularPredicateSerializer<?>> REGISTRY = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final RegularPredicateSerializer<NbtPredicate> NBT = RegularPredicateSerializer.delegated(NbtPredicate::fromJson, NbtPredicate::toJson);
    public static final RegularPredicateSerializer<StatePredicate> STATE = RegularPredicateSerializer.delegated(StatePredicate::fromJson, StatePredicate::toJson);
    public static final RegularPredicateSerializer<NumberRange.IntRange> INT_RANGE = RegularPredicateSerializer.delegated(NumberRange.IntRange::fromJson, NumberRange.IntRange::toJson);
    public static final RegularPredicateSerializer<NumberRange.FloatRange> FLOAT_RANGE = RegularPredicateSerializer.delegated(NumberRange.FloatRange::fromJson, NumberRange.FloatRange::toJson);

    public static final RegularPredicateSerializer<PredicateSchema> SCHEMA = RegularPredicateSerializer.delegated(PredicateSchema::fromJson, PredicateSchema::toJson);

    public static void register(Identifier id, RegularPredicateSerializer<?> serializer) {
        Registry.register(REGISTRY, id, serializer);
    }

    private static void registerMc(String name, RegularPredicateSerializer<?> serializer) {
        register(new Identifier(name), serializer);
    }

    private static void register(String name, RegularPredicateSerializer<?> serializer) {
        register(RegularPredicatesMod.id(name), serializer);
    }

    @ApiStatus.Internal
    public static void register() {
        registerMc("nbt", NBT);
        registerMc("state", STATE);
        registerMc("int_range", INT_RANGE);
        registerMc("float_range", FLOAT_RANGE);
        register("schema", SCHEMA);
    }
}
