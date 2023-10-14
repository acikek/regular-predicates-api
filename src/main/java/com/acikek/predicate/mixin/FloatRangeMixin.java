package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.wrapper.FloatRangeWrapper;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.predicate.NumberRange;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NumberRange.FloatRange.class)
public abstract class FloatRangeMixin implements FloatRangeWrapper {

    @Shadow public abstract boolean test(double value);

    @Override
    public @NotNull Class<Double> rp$contextType() {
        return Double.class;
    }

    @Override
    public @NotNull RegularPredicateSerializer<?> rp$serializer() {
        return RegularPredicates.FLOAT_RANGE;
    }

    @Override
    public boolean rp$test(Double aDouble) {
        return test(aDouble);
    }
}
