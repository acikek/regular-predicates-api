package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.wrapper.NbtPredicateWrapper;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.predicate.NbtPredicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtPredicate.class)
public abstract class NbtPredicateMixin implements NbtPredicateWrapper {

    @Shadow public abstract boolean test(@Nullable NbtElement element);

    @Override
    public @NotNull Class<NbtElement> rp$contextType() {
        return NbtElement.class;
    }

    @Override
    public @NotNull RegularPredicateSerializer<?> rp$serializer() {
        return RegularPredicates.NBT;
    }

    @Override
    public boolean rp$test(NbtElement element) {
        return test(element);
    }
}
