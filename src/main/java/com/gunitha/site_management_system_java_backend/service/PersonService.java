package com.gunitha.site_management_system_java_backend.service;


import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonService implements IPersonService{
    @Override
    public PersonInfo findByPersonInfo(Long personId) {
        return null;
    }

    @Override
    public List<PersonInfo> findByOrganisation(Long organisationId) {
        return List.of();
    }

    @Override
    public List<PersonInfo> findBySiteId(Long siteId) {
        return List.of();
    }

    @Override
    public void createPerson(PersonInfoUpdate personInfoUpdate) {

    }
}
