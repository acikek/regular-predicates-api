package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.wrapper.NbtPredicateWrapper;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.predicate.NbtPredicate;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtPredicate.class)
public abstract class NbtPredicateMixin implements NbtPredicateWrapper {

    @Override
    public Class<NbtElement> rp$contextType() {
        return NbtElement.class;
    }

    @Override
    public RegularPredicateSerializer<? extends RegularPredicate<NbtElement>> rp$serializer() {
        return RegularPredicates.NBT;
    }
}
