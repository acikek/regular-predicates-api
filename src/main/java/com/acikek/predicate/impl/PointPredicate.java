package com.acikek.predicate.impl;

import com.acikek.predicate.RegularPredicatesMod;
import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.serializer.PredicateSerializers;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.Registry;

// proof of concept
public record PointPredicate(int x, int y) implements RegularPredicate<PointPredicate.Context> {

    public static final Codec<PointPredicate> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("x").forGetter(PointPredicate::x),
                    Codec.INT.fieldOf("y").forGetter(PointPredicate::y)
            ).apply(instance, PointPredicate::new)
    );

    public static final RegularPredicateSerializer<PointPredicate> SERIALIZER = PredicateSerializers.codec(CODEC);

    public record Context(int x, int y) {}

    @Override
    public Class<Context> rp$contextType() {
        return Context.class;
    }

    @Override
    public boolean rp$test(Context context) {
        return x == context.x && y == context.y;
    }

    @Override
    public RegularPredicateSerializer<? extends RegularPredicate<Context>> rp$serializer() {
        return SERIALIZER;
    }

    public static void register() {
        Registry.register(RegularPredicates.REGISTRY, RegularPredicatesMod.id("point"), SERIALIZER);
    }
}
