package com.acikek.predicate;

import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.schema.map.PredicateMap;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegularPredicatesMod implements ModInitializer {

    public static final String ID = "regular_predicates";

    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

    @Override
    public void onInitialize() {
        RegularPredicates.register();

        NbtCompound nbt = new NbtCompound();
        nbt.putInt("count", 5);
        NbtPredicate nbtPredicate = new NbtPredicate(nbt);

        StatePredicate statePredicate = StatePredicate.Builder.create()
                .exactMatch(Properties.CANDLES, 3)
                .exactMatch(Properties.POWERED, true)
                .exactMatch(Properties.FACING, "north")
                .build();

        var intRange = NumberRange.IntRange.between(10, 200);

        var map = PredicateMap.of(ImmutableMap.of(
                "nbt", nbtPredicate,
                "number", intRange,
                "state", statePredicate
        ));

        System.out.println(map.test(nbt, 100, Blocks.BAMBOO.getDefaultState()));
    }
}
