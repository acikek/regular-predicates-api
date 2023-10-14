package com.acikek.predicate.api.schema;

import com.acikek.predicate.api.FriendlyPredicate;
import com.acikek.predicate.api.schema.map.PredicateMapFunny;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.JsonElement;
import net.minecraft.network.PacketByteBuf;

import java.util.Collection;

public record PredicateSchema(Collection<SchemaElement> elements) implements FriendlyPredicate<PredicateMapFunny> {

    @Override
    public Class<PredicateMapFunny> contextType() {
        return PredicateMapFunny.class;
    }

    @Override
    public RegularPredicateSerializer<?> serializer() {
        return null;
    }

    @Override
    public boolean test(PredicateMapFunny map) {
        return SchemaElement.test(elements, map);
    }

    public static class Serializer implements RegularPredicateSerializer<PredicateSchema> {

        @Override
        public PredicateSchema fromJson(JsonElement json) {
            return
        }

        @Override
        public JsonElement toJson(PredicateSchema instance) {
            return null;
        }

        @Override
        public PredicateSchema read(PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, PredicateSchema instance) {

        }
    }
}
