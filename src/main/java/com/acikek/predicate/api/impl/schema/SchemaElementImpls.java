package com.acikek.predicate.api.impl.schema;

import com.acikek.predicate.api.schema.SchemaElement;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public class SchemaElementImpls {

    public record Predicate(String name, RegularPredicateSerializer<?> type) implements SchemaElement {

        @Override
        public Collection<SchemaElement> children() {
            return Collections.emptyList();
        }
    }

    public record Mapping(String name, Collection<SchemaElement> children) implements SchemaElement {

        @Override
        public @Nullable RegularPredicateSerializer<?> type() {
            return null;
        }
    }
}
