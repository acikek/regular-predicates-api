package com.acikek.predicate.api.schema;

import com.acikek.predicate.api.RegularPredicate;
import com.acikek.predicate.api.RegularPredicates;
import com.acikek.predicate.api.impl.schema.SchemaElementImpls;
import com.acikek.predicate.api.schema.map.PredicateMap;
import com.acikek.predicate.api.serializer.RegularPredicateSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public interface SchemaElement {

    String name();

    @Nullable RegularPredicateSerializer<?> type();

    Collection<SchemaElement> children();

    // Neat, these methods call each other
    static boolean test(Collection<SchemaElement> elements, PredicateMap map) {
        for (var element : elements) {
            var predicate = map.predicates().get(element.name());
            if (predicate == null || !element.test(predicate)) {
                return false;
            }
        }
        return true;
    }

    default boolean test(RegularPredicate<?> predicate) {
        if (predicate instanceof PredicateMap innerMap && !children().isEmpty()) {
            return test(children(), innerMap);
        }
        return predicate.rp$serializer() == type();
    }

    static SchemaElement fromJson(JsonObject obj) {
        var name = JsonHelper.getString(obj, "name");
        var type = JsonHelper.getElement(obj, "type");
        if (JsonHelper.isString(type)) {
            var id = new Identifier(type.getAsString());
            var serializer = RegularPredicates.REGISTRY.get(id);
            if (serializer == null) {
                throw new NoSuchElementException("serializer '" + id + "' does not exist");
            }
            return SchemaElement.type(name, serializer);
        }
        if (!type.isJsonArray()) {
            throw new JsonSyntaxException("element type must be a serializer ID or a new schema array");
        }
        var jsonArray = type.getAsJsonArray();
        if (jsonArray.isEmpty()) {
            throw new JsonSyntaxException("element mapping must not be empty");
        }
        List<SchemaElement> children = new ArrayList<>();
        for (var element : jsonArray) {
            var subObj = JsonHelper.asObject(element, "child element");
            children.add(fromJson(subObj));
        }
        return SchemaElement.map(name, children);
    }

    static Collection<SchemaElement> fromMap(Map<String, RegularPredicate<?>> map) {
        List<SchemaElement> elements = new ArrayList<>();
        for (var entry : map.entrySet()) {
            var element = SchemaElement.fromEntry(entry.getKey(), entry.getValue());
            elements.add(element);
        }
        return elements;
    }

    static SchemaElement fromEntry(String name, RegularPredicate<?> predicate) {
        if (predicate instanceof PredicateMap map) {
            return SchemaElement.map(name, fromMap(map.predicates()));
        }
        return SchemaElement.type(name, predicate.rp$serializer());
    }

    default JsonObject toJson() {
        var obj = new JsonObject();
        obj.add("name", new JsonPrimitive(name()));
        if (type() != null) {
            var id = RegularPredicates.REGISTRY.getId(type());
            if (id == null) {
                throw new JsonSyntaxException("element type is not registered");
            }
            obj.add("type", new JsonPrimitive(id.toString()));
            return obj;
        }
        if (children().isEmpty()) {
            throw new JsonSyntaxException("element must either have a predicate type or children");
        }
        var array = new JsonArray();
        for (var child : children()) {
            array.add(child.toJson());
        }
        obj.add("type", array);
        return obj;
    }

    default RegularPredicate<?> deserialize(JsonElement json) {
        if (type() != null) {
            return type().fromJson(json);
        }
        else if (children().isEmpty()) {
            var schema = new PredicateSchema(children());
            return schema.deserialize(json);
        }
        throw new JsonSyntaxException("element must either have a predicate type or children");
    }

    static SchemaElement type(String name, RegularPredicateSerializer<?> type) {
        return new SchemaElementImpls.Predicate(name, type);
    }

    static SchemaElement map(String name, Collection<SchemaElement> children) {
        return new SchemaElementImpls.Mapping(name, children);
    }
}
