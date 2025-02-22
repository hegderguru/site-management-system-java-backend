package com.gunitha.site_management_system_java_backend.util;

import com.gunitha.site_management_system_java_backend.model.change.Change;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service("changes")
public class ChangesUtil {

    public void getChanges(Object oldObject,Object newObject) throws IllegalAccessException {
        Change change = getChange(oldObject, newObject);
        if(isCustomType(oldObject.getClass())){
            Field[] leftFields = oldObject.getClass().getFields();
            for(Field field:leftFields){
                Field rightField = getRespectiveField(field,newObject.getClass().getFields());
                if(field.getClass().getTypeName().equals(List.class.getTypeName())){
                    List objects = (List) field.get(oldObject);
                    for(Object object: objects){
                        getChange(object,getRespectiveObject(object,(List) field.get(newObject)));
                    }
                }
                else {
                    change.getChanges().put(field,Change.builder().left(field).right(rightField).build());
                }
            }
        }
    }

    private Object getRespectiveObject(Object o1,List<Object> o2) {


        return o2.stream().filter(o -> o.equals(o1)).findFirst();
    }

    private Field getRespectiveField(Field leftField, Field[] fields) {
        for(Field field:fields){
            if(field.getName().equals(leftField.getName())){
                return field;
            }
        }
    }

    public Change getChange(Object oldObject,Object newObject){

        return Change.builder().left(oldObject).right(newObject).changes(new HashMap<>()).build();

    }

    public static boolean isCustomType(Class clazz){
        return !(clazz.isPrimitive() || clazz.getName().startsWith("java."));
    }

}
