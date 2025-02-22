package com.gunitha.site_management_system_java_backend.model.change;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Change {
    private String id;
    private Object leftObject;
    private Object rightObject;
    private String fieldName;
    private Object leftValue;
    private Object rightValue;
    private String path;
}
