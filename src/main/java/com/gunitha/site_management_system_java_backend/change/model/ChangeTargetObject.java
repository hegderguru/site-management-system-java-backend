package com.gunitha.site_management_system_java_backend.change.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeTargetObject {
    private Object targetObject;
    private String id;
    private Field field;
    private String path;
    private Object value;
}
