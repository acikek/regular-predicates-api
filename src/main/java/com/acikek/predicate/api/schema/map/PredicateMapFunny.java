package com.acikek.predicate.api.schema.map;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;

import java.util.Map;

@SuppressWarnings("unchecked")
public record PredicateMapFunny
        (ImmutableMap<String, RegularPredicate<?>> predicates)
        implements FriendlyPredicate<Map<String, Object>> {

    @Override
    public Class<Map<String, Object>> contextType() {
        return (Class<Map<String, Object>>) (Class<?>) Map.class;
    }

    @Override
    public RegularPredicateSerializer<?> serializer() {
        return null;
    }

    @Override
    public boolean test(Map<String, Object> stringObjectMap) {
        return false;
    }

    public static class PredicateMapSerializer implements RegularPredicateSerializer<PredicateMapFunny> {

        @Override
        public PredicateMapFunny fromJson(JsonElement json) {
            return null;
        }

        @Override
        public JsonElement toJson(PredicateMapFunny instance) {
            JsonObject obj = new JsonObject();
            for (var entry : instance.predicates.entrySet()) {
                obj.add(entry.getKey(), RegularPredicates.toJson(entry.getValue()));
            }
            return obj;
        }

        @Override
        public PredicateMapFunny read(PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, PredicateMapFunny instance) {

        }
    }
}
