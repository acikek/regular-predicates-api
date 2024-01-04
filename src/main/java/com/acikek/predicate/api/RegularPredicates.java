package com.acikek.predicate.api;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.enums.EnumPredicateSerializer;
import com.acikek.predicate.api.schema.PredicateSchema;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.EightWayDirection;
import org.jetbrains.annotations.ApiStatus;

public class RegularPredicates {

    public static final RegistryKey<Registry<RegularPredicateSerializer<?>>> REGISTRY_KEY = RegistryKey.ofRegistry(RegularPredicatesMod.id("predicate"));
    public static final Registry<RegularPredicateSerializer<?>> REGISTRY = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final RegularPredicateSerializer<NbtPredicate> NBT = RegularPredicateSerializer.delegated(NbtPredicate::fromJson, NbtPredicate::toJson);
    public static final RegularPredicateSerializer<StatePredicate> STATE = RegularPredicateSerializer.delegated(StatePredicate::fromJson, StatePredicate::toJson);
    public static final RegularPredicateSerializer<NumberRange.IntRange> INT_RANGE = RegularPredicateSerializer.delegated(NumberRange.IntRange::fromJson, NumberRange.IntRange::toJson);
    public static final RegularPredicateSerializer<NumberRange.FloatRange> FLOAT_RANGE = RegularPredicateSerializer.delegated(NumberRange.FloatRange::fromJson, NumberRange.FloatRange::toJson);

    public static final EnumPredicateSerializer<Direction> DIRECTION = RegularPredicateSerializer.forEnum(Direction.class);
    public static final EnumPredicateSerializer<Direction.Axis> AXIS = RegularPredicateSerializer.forEnum(Direction.Axis.class);
    public static final EnumPredicateSerializer<Direction.AxisDirection> AXIS_DIRECTION = RegularPredicateSerializer.forEnum(Direction.AxisDirection.class);
    public static final EnumPredicateSerializer<Direction.Type> DIRECTION_TYPE = RegularPredicateSerializer.forEnum(Direction.Type.class);
    public static final EnumPredicateSerializer<EightWayDirection> EIGHT_WAY_DIRECTION = RegularPredicateSerializer.forEnum(EightWayDirection.class);
    public static final EnumPredicateSerializer<Hand> HAND = RegularPredicateSerializer.forEnum(Hand.class);
    public static final EnumPredicateSerializer<DyeColor> DYE_COLOR = RegularPredicateSerializer.forEnum(DyeColor.class);
    public static final EnumPredicateSerializer<Formatting> FORMATTING = RegularPredicateSerializer.forEnum(Formatting.class);
    public static final EnumPredicateSerializer<UseAction> USE_ACTION = RegularPredicateSerializer.forEnum(UseAction.class);

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
        registerMc("direction", DIRECTION);
        registerMc("axis", AXIS);
        registerMc("axis_direction", AXIS_DIRECTION);
        registerMc("direction_type", DIRECTION_TYPE);
        registerMc("eight_way_direction", EIGHT_WAY_DIRECTION);
        registerMc("hand", HAND);
        registerMc("dye_color", DYE_COLOR);
        registerMc("formatting", FORMATTING);
        registerMc("use_action", USE_ACTION);
        register("schema", SCHEMA);
    }
}
