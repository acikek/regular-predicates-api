package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.wrapper.IntRangeWrapper;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.predicate.NumberRange;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NumberRange.IntRange.class)
public class IntRangeMixin implements IntRangeWrapper {

    @Override
    public Class<Integer> rp$contextType() {
        return Integer.class;
    }

    @Override
    public RegularPredicateSerializer<?> rp$serializer() {
        return RegularPredicates.INT;
    }
}
