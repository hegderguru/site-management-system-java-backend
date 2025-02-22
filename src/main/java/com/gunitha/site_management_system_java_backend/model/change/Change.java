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

    private Object left;
    private Object right;
    private Field field;
    private Map<Object,Change> changes;
    private boolean changeFound;

}
