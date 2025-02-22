package com.gunitha.site_management_system_java_backend.mapper.entityToRequest;

import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonInfoUpdateMapper {

    private final static ModelMapper personInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Person, PersonInfoUpdate> typeMap = personInfoModelMapper.createTypeMap(Person.class, PersonInfoUpdate.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Person::getId, PersonInfoUpdate::setId);
            mapper.map(Person::getFirstName, PersonInfoUpdate::setFirstName);
            mapper.map(Person::getMiddleName, PersonInfoUpdate::setMiddleName);
            mapper.map(Person::getLastName, PersonInfoUpdate::setLastName);
            mapper.map(Person::getGender, PersonInfoUpdate::setGender);
            mapper.map(Person::getDateOfBirth, PersonInfoUpdate::setDateOfBirth);
        });
    }

    public PersonInfoUpdate personInfo(Person person) {
        return personInfoModelMapper.map(person, PersonInfoUpdate.class);
    }

}
