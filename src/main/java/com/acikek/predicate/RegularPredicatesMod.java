package com.acikek.predicate;

import com.acikek.predicate.api.impl.map.PredicateMapImpl;
import com.acikek.predicate.api.map.PredicateMap;
import com.acikek.predicate.impl.PointPredicate;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
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
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("count", 5);
        PredicateMap map = new PredicateMapImpl(
                ImmutableMap.of(
                        "nbt", new NbtPredicate(nbt),
                        "point", new PointPredicate(3, 10)
                )
        );
        System.out.println(map.test(nbt, new PointPredicate.Context(3, 10)));
    }
}
