package com.gunitha.site_management_system_java_backend.service;

import com.gunitha.site_management_system_java_backend.change.ChangesUtil;
import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeTargetObject;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.mapper.entityToRead.SiteInfoReadMapper;
import com.gunitha.site_management_system_java_backend.mapper.entityToRequest.SiteInfoUpdateMapper;
import com.gunitha.site_management_system_java_backend.mapper.updateToEntity.*;
import com.gunitha.site_management_system_java_backend.model.read.SiteInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.IOrganisationRepository;
import com.gunitha.site_management_system_java_backend.repository.IPersonRepository;
import com.gunitha.site_management_system_java_backend.repository.ISiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("siteService")
public class SiteService implements ISiteService {

    @Autowired
    ISiteRepository iSiteRepository;

    @Autowired
    IPersonRepository iPersonRepository;

    @Autowired
    IOrganisationRepository iOrganisationRepository;

    @Autowired
    SiteInfoReadMapper siteInfoReadMapper;

    @Autowired
    SiteInfoUpdateMapper siteInfoUpdateMapper;

    @Autowired
    SiteInfoUpdateEntityMapper siteInfoUpdateEntityMapper;

    @Autowired
    AddressInfoUpdateEntityMapper addressInfoUpdateEntityMapper;

    @Autowired
    PersonInfoUpdateEntityMapper personInfoUpdateEntityMapper;

    @Autowired
    OrganisationInfoUpdateEntityMapper organisationInfoUpdateEntityMapper;

    @Autowired
    LocationInfoUpdateEntityMapper locationInfoUpdateEntityMapper;

    @Override
    public List<SiteInfo> findAllSites() {
        return iSiteRepository.findAll().stream().map(site -> siteInfoReadMapper.siteInfo(site)).toList();
    }

    @Override
    public SiteInfo findBySiteId(Long siteId) {
        return siteInfoReadMapper.siteInfo(iSiteRepository.findById(siteId).get());
    }

    @Override
    public List<SiteInfo> findByPerson(Long personId) {
        List<SiteInfo> siteInfos = iSiteRepository.findSitesByOwnersId(personId).stream().map(site -> siteInfoReadMapper.siteInfo(site)).toList();
        siteInfos.addAll(iPersonRepository.findById(personId).get().getOrganisations().stream().flatMap(organisation -> organisation.getSites().stream())
                .map(site -> siteInfoReadMapper.siteInfo(site)).toList());
        return siteInfos;
    }

    @Override
    public List<SiteInfo> findByOrganisation(Long organisationId) {
        return iOrganisationRepository.findById(organisationId).get().getSites().stream().map(site -> siteInfoReadMapper.siteInfo(site)).toList();
    }

    @Override
    public SiteInfo createSite(SiteInfoUpdate siteInfoUpdate) {
        return siteInfoReadMapper.siteInfo(siteInfoUpdateEntityMapper.newSiteEntity(siteInfoUpdate));
    }

    @Override
    public SiteInfo updateSite(SiteInfoUpdate updatedSiteInfoUpdate) {
        SiteInfoUpdate existingSiteInfoUpdate = siteInfoUpdateMapper.siteInfo(iSiteRepository.findById(updatedSiteInfoUpdate.getId()).get());
        List<ChangeTargetObject> existing = ChangesUtil.getAllCustomTypeObjects(existingSiteInfoUpdate, "SiteInfoUpdate");
        List<ChangeTargetObject> updated = ChangesUtil.getAllCustomTypeObjects(updatedSiteInfoUpdate, "SiteInfoUpdate");
        List<Change> changes = ChangesUtil.getChanges(existing, updated);
        Site site = iSiteRepository.findById(updatedSiteInfoUpdate.getId()).get();
        personInfoUpdateEntityMapper.updateOrPersonNewLocation(changes, null);
        addressInfoUpdateEntityMapper.updateOrAddNewAddressEntity(changes, site, null);
        siteInfoUpdateEntityMapper.updateOrAddNewSite(changes, site);
        organisationInfoUpdateEntityMapper.updateOrAddNewOrganisation(changes, null);
        locationInfoUpdateEntityMapper.updateOrAddNewLocation(changes, site);
        iSiteRepository.save(site);
        return siteInfoReadMapper.siteInfo(site);
    }
}
