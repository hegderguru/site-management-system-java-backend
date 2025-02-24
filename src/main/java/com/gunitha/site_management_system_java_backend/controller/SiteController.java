package com.gunitha.site_management_system_java_backend.controller;

import com.gunitha.site_management_system_java_backend.model.read.SiteInfo;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import com.gunitha.site_management_system_java_backend.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/site")
public class SiteController {

    @Autowired
    ISiteService iSiteService;

    public List<SiteInfo> findBySiteIds(List<Long> siteIds) {
        return iSiteService.findBySiteIds(siteIds);
    }

    public SiteInfo findBySiteId(Long siteId) {
        return iSiteService.findBySiteId(siteId);
    }

    public List<SiteInfo> findByPerson(Long personId) {
        return iSiteService.findByPerson(personId);
    }

    public List<SiteInfo> findByOrganisation(Long organisationId) {
        return iSiteService.findByOrganisation(organisationId);
    }

    public SiteInfo createSite(SiteInfoUpdate siteInfoUpdate) {
        return iSiteService.createSite(siteInfoUpdate);
    }

    public SiteInfo updateSite(SiteInfoUpdate siteInfoUpdate) {
        return iSiteService.updateSite(siteInfoUpdate);
    }

}
