package com.gunitha.site_management_system_java_backend.service;

import com.gunitha.site_management_system_java_backend.read.SiteInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("siteService")
public class SiteService implements ISiteService {

    @Override
    public List<SiteInfo> findAllSites() {
        return List.of();
    }

    @Override
    public List<SiteInfo> findBySiteId(Long siteId) {
        return List.of();
    }

    @Override
    public List<SiteInfo> findByPerson(Long personId) {
        return List.of();
    }

    @Override
    public List<SiteInfo> findByOrganisation(Long organisationId) {
        return List.of();
    }

    @Override
    public SiteInfo createSite(SiteInfo siteInfo) {
        return null;
    }
}
