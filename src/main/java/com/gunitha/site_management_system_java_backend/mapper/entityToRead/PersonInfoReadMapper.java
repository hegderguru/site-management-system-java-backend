package com.gunitha.site_management_system_java_backend.mapper.entityToRead;

import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Slf4j
@Service("personInfoReadMapper")
public class PersonInfoReadMapper {

    private final static ModelMapper personInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Person, PersonInfo> typeMap = personInfoModelMapper.createTypeMap(Person.class, PersonInfo.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Person::getId, PersonInfo::setId);
            mapper.map(Person::getFirstName, PersonInfo::setFirstName);
            mapper.map(Person::getMiddleName, PersonInfo::setMiddleName);
            mapper.map(Person::getLastName, PersonInfo::setLastName);
            mapper.map(Person::getGender, PersonInfo::setGender);
            mapper.map(Person::getDateOfBirth, PersonInfo::setDateOfBirth);
        });
    }

    public PersonInfo personInfo(Person person) {
        return personInfoModelMapper.map(person, PersonInfo.class);
    }

}
