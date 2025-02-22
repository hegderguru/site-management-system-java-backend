package com.gunitha.site_management_system_java_backend.util;

import com.gunitha.site_management_system_java_backend.model.change.ChangeTargetObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ChangesUtil {

    public static List<ChangeTargetObject> getAllCustomTypeObjects(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields()).flatMap(field -> {
            try {
                field.setAccessible(true);
                if (Objects.nonNull(field.get(object))) {
                    if (isCustomType(field.get(object).getClass()) || field.get(object).getClass().getName().equals(ArrayList.class.getName())) {
                        if (field.get(object).getClass().getName().equals(ArrayList.class.getName())) {
                            return ((ArrayList<Object>) field.get(object)).stream().flatMap(o -> getAllCustomTypeObjects(o).stream()).toList().stream();
                        } else {
                            if (field.get(object).getClass().getName().startsWith("com.gunitha.")) {
                                return getAllCustomTypeObjects(field.get(object)).stream();
                            }
                        }
                    } else {
                        return Stream.of(new ChangeTargetObject(object, field));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return java.util.stream.Stream.empty();
        }).toList();
    }

    public static boolean isCustomType(Class clazz) {
        return !(clazz.isPrimitive() || clazz.getName().startsWith("java."));
    }

}
