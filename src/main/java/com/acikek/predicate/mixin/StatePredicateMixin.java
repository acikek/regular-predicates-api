package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.State;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unchecked")
@Mixin(StatePredicate.class)
public abstract class StatePredicateMixin implements RegularPredicate<State<?, ?>> {

    @Shadow public abstract boolean test(BlockState state);

    @Shadow public abstract boolean test(FluidState state);

    @Override
    public Class<State<?, ?>> contextType() {
        return (Class<State<?, ?>>) (Class<?>) State.class;
    }

    @Override
    public RegularPredicateSerializer<? extends RegularPredicate<State<?, ?>>> serializer() {
        return RegularPredicates.STATE;
    }

    @Override
    public boolean test(State<?, ?> state) {
        if (state instanceof BlockState blockState) {
            return test(blockState);
        }
        if (state instanceof FluidState fluidState) {
            return test(fluidState);
        }
        return false;
    }
}
