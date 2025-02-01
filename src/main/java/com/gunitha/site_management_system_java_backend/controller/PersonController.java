package com.gunitha.site_management_system_java_backend.controller;

import com.gunitha.site_management_system_java_backend.read.PersonInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/person")
public class PersonController {

    public PersonInfo findByPersonInfo(Long personId) {
        return null;
    }

    public List<PersonInfo> findByOrganisation(Long organisationId) {
        return List.of();
    }

    public List<PersonInfo> findBySiteId(Long siteId) {
        return List.of();
    }

}
