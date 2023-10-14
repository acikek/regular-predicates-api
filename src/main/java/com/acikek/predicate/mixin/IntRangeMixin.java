package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.wrapper.IntRangeWrapper;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.predicate.NumberRange;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NumberRange.IntRange.class)
public abstract class IntRangeMixin implements IntRangeWrapper {

    @Shadow public abstract boolean test(int value);

    @Override
    public @NotNull Class<Integer> rp$contextType() {
        return Integer.class;
    }

    @Override
    public @NotNull RegularPredicateSerializer<?> rp$serializer() {
        return RegularPredicates.INT_RANGE;
    }

    @Override
    public boolean rp$test(Integer integer) {
        return test(integer);
    }
}
