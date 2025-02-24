package com.gunitha.site_management_system_java_backend.change.model;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class ChangeValueExtractUtil {

    public static String extractString(Object object){
        if(object instanceof String string){
            return string;
        }
        return null;
    }

    public static Double extractDouble(Object object) {
        if(object instanceof Double aDouble){
            return aDouble;
        }
        return null;
    }

    public static LocalDateTime extractLocalDateTime(Object object) {
        if(object instanceof LocalDateTime localDateTime){
            return localDateTime;
        }
        return null;
    }
}
