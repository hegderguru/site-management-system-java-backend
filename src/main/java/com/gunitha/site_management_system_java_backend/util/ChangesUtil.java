package com.gunitha.site_management_system_java_backend.util;

import com.gunitha.site_management_system_java_backend.model.change.ChangeTargetObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ChangesUtil {

    public static List<ChangeTargetObject> getAllCustomTypeObjects(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields()).filter(field -> {
            try {
                return Objects.nonNull(field.get(object)) && isCustomType(field.getClass());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(field -> {
            try {
                return getAllCustomTypeObjects(field.get(object)).stream();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public static boolean isCustomType(Class clazz){
        return !(clazz.isPrimitive() || clazz.getName().startsWith("java."));
    }

}
