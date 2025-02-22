package com.gunitha.site_management_system_java_backend.util;

import com.gunitha.site_management_system_java_backend.model.change.Change;
import com.gunitha.site_management_system_java_backend.model.change.ChangeTargetObject;
import com.gunitha.site_management_system_java_backend.model.change.ChangeTargetObjectId;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangesUtil {

    public static List<ChangeTargetObject> getAllCustomTypeObjects(Object object, String path) {
        return Arrays.stream(object.getClass().getDeclaredFields()).flatMap(field -> {
            try {
                field.setAccessible(true);
                if (Objects.nonNull(field.get(object))) {
                    if (isCustomType(field.get(object).getClass()) || field.get(object).getClass().getName().equals(ArrayList.class.getName())) {
                        if (field.get(object).getClass().getName().equals(ArrayList.class.getName())) {
                            return ((ArrayList<Object>) field.get(object)).stream().flatMap(o -> getAllCustomTypeObjects(o, field.getName()).stream()).toList().stream();
                        } else {
                            if (field.get(object).getClass().getName().startsWith("com.gunitha.")) {
                                return getAllCustomTypeObjects(field.get(object), field.getName()).stream();
                            }
                        }
                    } else {
                        return Stream.of(new ChangeTargetObject(object, getChangeTargetObjectId(object),field, path + "." + field.getName(), field.get(object)));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return java.util.stream.Stream.empty();
        }).toList();
    }

    private static String getChangeTargetObjectId(Object object) {
        List<Field> idFields = Arrays.stream(object.getClass().getDeclaredFields()).filter(field -> {
            field.setAccessible(true);
            return Arrays.stream(field.getAnnotations())
                    .anyMatch(annotation -> annotation.annotationType().equals(ChangeTargetObjectId.class));
        }).toList();
        List<Field> idFieldsSorted = idFields.stream().sorted((field1, field2) -> {
            field1.setAccessible(true);
            field2.setAccessible(true);
            try {
                return field1.get(object).getClass().getName().compareTo(field2.get(object).getClass().getName());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return idFieldsSorted.stream().map(field -> {
            try {
                return field.get(object).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining());
    }

    public static boolean isCustomType(Class clazz) {
        return !(clazz.isPrimitive() || clazz.getName().startsWith("java."));
    }

    public static List<Change> getChanges(List<ChangeTargetObject> existing, List<ChangeTargetObject> updated) {
        return updated.stream().map(changeTargetObject -> {
            Optional<ChangeTargetObject> changeTargetObjectOptional = existing.stream().filter(changeTargetObject1 -> changeTargetObject.getPath().equals(changeTargetObject1.getPath()) && changeTargetObject.getId().equals(changeTargetObject1.getId())).findFirst();
            if (changeTargetObjectOptional.isPresent()) {
                if (!changeTargetObjectOptional.get().getValue().equals(changeTargetObject.getValue())) {
                    return Change.builder().id(changeTargetObject.getId()).path(changeTargetObject.getPath()).leftObject(changeTargetObjectOptional.get().getTargetObject())
                            .rightObject(changeTargetObject.getTargetObject()).fieldName(changeTargetObject.getField().getName()).leftValue(changeTargetObjectOptional.get().getValue()).rightValue(changeTargetObject.getValue())
                            .build();
                }
            } else {
                return Change.builder().id(changeTargetObject.getId()).path(changeTargetObject.getPath()).rightObject(changeTargetObject).build();
            }
            return null;
        }).filter(Objects::nonNull).toList();
    }

}
