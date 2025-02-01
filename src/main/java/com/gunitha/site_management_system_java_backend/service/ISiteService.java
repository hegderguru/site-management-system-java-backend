package com.gunitha.site_management_system_java_backend.service;

import com.gunitha.site_management_system_java_backend.read.SiteInfo;

import java.util.List;

public interface ISiteService {

    public List<SiteInfo> findAllSites();

    public List<SiteInfo> findBySiteId();

    public List<SiteInfo> findByPerson(Long personId);

    public List<SiteInfo> findByOrganisation(Long organisationId);

}
