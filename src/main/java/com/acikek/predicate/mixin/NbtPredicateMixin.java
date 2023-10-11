package com.acikek.predicate.mixin;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.predicate.NbtPredicate;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NbtPredicate.class)
public class NbtPredicateMixin implements RegularPredicate<NbtElement> {

    @Override
    public Class<NbtElement> rp$contextType() {
        return NbtElement.class;
    }

    @Override
    public RegularPredicateSerializer<? extends RegularPredicate<NbtElement>> rp$serializer() {
        return RegularPredicates.NBT;
    }

    @Override
    public boolean rp$test(NbtElement nbtElement) {
        return ((NbtPredicate) (Object) this).test(nbtElement);
    }
}
