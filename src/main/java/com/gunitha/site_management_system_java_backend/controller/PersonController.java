package com.gunitha.site_management_system_java_backend.controller;

import com.gunitha.site_management_system_java_backend.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/person")
public class PersonController {

    @Autowired
    IPersonService iPersonService;

    public PersonInfo findByPersonInfo(Long personId) {
        return iPersonService.findByPersonInfo(personId);
    }

    public List<PersonInfo> findByOrganisation(Long organisationId) {
        return iPersonService.findByOrganisation(organisationId);
    }

    public List<PersonInfo> findBySiteId(Long siteId) {
        return iPersonService.findBySiteId(siteId);
    }

}
