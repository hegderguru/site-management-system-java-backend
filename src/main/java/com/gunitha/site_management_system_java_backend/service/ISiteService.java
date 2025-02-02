package com.gunitha.site_management_system_java_backend.service;

import com.gunitha.site_management_system_java_backend.model.read.SiteInfo;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;

import java.util.List;

public interface ISiteService {

    public List<SiteInfo> findAllSites();

    public List<SiteInfo> findBySiteId(Long siteId);

    public List<SiteInfo> findByPerson(Long personId);

    public List<SiteInfo> findByOrganisation(Long organisationId);

    public SiteInfo createSite(SiteInfoUpdate siteInfoUpdate);

    public SiteInfo updateSite(SiteInfoUpdate siteInfoUpdate);

}
