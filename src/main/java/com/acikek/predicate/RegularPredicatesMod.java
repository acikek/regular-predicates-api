package com.acikek.predicate;

import com.acikek.predicate.api.RegularPredicates;
import net.fabricmc.api.ModInitializer;
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
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("count", 5);
        NbtPredicate predicate = new NbtPredicate(nbt);
        System.out.println(RegularPredicates.serializer(predicate).toJson(predicate));
        System.out.println(predicate.test(nbt));

        StatePredicate statePredicate = StatePredicate.Builder.create()
                .exactMatch(Properties.CANDLES, 3)
                .exactMatch(Properties.POWERED, true)
                .exactMatch(Properties.FACING, "north")
                .build();

        var json = RegularPredicates.toJson(statePredicate);
        var newState = RegularPredicates.STATE.fromJson(json);
        System.out.println(json);
        System.out.println(RegularPredicates.toJson(newState));

        var intRange = NumberRange.IntRange.between(10, 200);
        System.out.println(RegularPredicates.toJson(intRange));

        /*PredicateMap map = new PredicateMapImpl(
                ImmutableMap.of(
                        "nbt", new NbtPredicate(nbt),
                        "point", new PointPredicate(3, 10)
                )
        );
        System.out.println(map.test(nbt, new PointPredicate.Context(3, 10)));*/
    }
}
