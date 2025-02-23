package com.gunitha.site_management_system_java_backend.service;


import com.gunitha.site_management_system_java_backend.mapper.entityToRead.PersonInfoReadMapper;
import com.gunitha.site_management_system_java_backend.mapper.entityToRequest.PersonInfoUpdateMapper;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonService implements IPersonService {

    @Autowired
    PersonInfoReadMapper personInfoReadMapper;

    @Autowired
    PersonInfoUpdateMapper personInfoUpdateMapper;

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonInfo findByPersonInfo(Long personId) {
        return personInfoReadMapper.personInfo(personRepository.findById(personId).get());
    }

    @Override
    public List<PersonInfo> findPersonsByOrganisationId(Long organisationId) {
        return personRepository.findPersonsByOrganisationId(organisationId).stream()
                .map(person -> personInfoReadMapper.personInfo(person)).toList();
    }

    @Override
    public List<PersonInfo> findBySiteId(Long siteId) {
        return List.of();
    }

    @Override
    public void createPerson(PersonInfoUpdate personInfoUpdate) {

    }
}
