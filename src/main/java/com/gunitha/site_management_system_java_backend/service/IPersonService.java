package com.gunitha.site_management_system_java_backend.service;

import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;

import java.util.List;

public interface IPersonService {

    public PersonInfo findByPersonInfo(Long personId);

    public List<PersonInfo> findByOrganisation(Long organisationId);

    public List<PersonInfo> findBySiteId(Long siteId);

    void createPerson(PersonInfoUpdate personInfoUpdate);
}
