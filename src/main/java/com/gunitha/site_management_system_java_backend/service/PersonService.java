package com.gunitha.site_management_system_java_backend.service;


import com.gunitha.site_management_system_java_backend.mapper.entityToRead.PersonInfoReadMapper;
import com.gunitha.site_management_system_java_backend.mapper.entityToRequest.PersonInfoUpdateMapper;
import com.gunitha.site_management_system_java_backend.mapper.updateToEntity.PersonInfoUpdateEntityMapper;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.IPersonRepository;
import com.gunitha.site_management_system_java_backend.repository.ISiteRepository;
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
    IPersonRepository iPersonRepository;

    @Autowired
    ISiteRepository iSiteRepository;

    @Autowired
    PersonInfoUpdateEntityMapper personInfoUpdateEntityMapper;

    @Override
    public PersonInfo findByPersonInfo(Long personId) {
        return personInfoReadMapper.personInfo(iPersonRepository.findById(personId).get());
    }

    @Override
    public List<PersonInfo> findPersonsByOrganisationId(Long organisationId) {
        return iPersonRepository.findPersonsByOrganisationId(organisationId).stream()
                .map(person -> personInfoReadMapper.personInfo(person)).toList();
    }

    @Override
    public List<PersonInfo> findBySiteId(Long siteId) {
        return iPersonRepository.findPersonBySitesId(siteId).stream().map(person -> personInfoReadMapper.personInfo(person)).toList();
    }

    @Override
    public PersonInfo createPerson(PersonInfoUpdate personInfoUpdate) {
        return personInfoReadMapper.personInfo(personInfoUpdateEntityMapper.newPersonEntity(personInfoUpdate));
    }
}
