package com.gunitha.site_management_system_java_backend.service;


import com.gunitha.site_management_system_java_backend.change.ChangesUtil;
import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeTargetObject;
import com.gunitha.site_management_system_java_backend.entity.Organisation;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.mapper.entityToRead.PersonInfoReadMapper;
import com.gunitha.site_management_system_java_backend.mapper.entityToRequest.PersonInfoUpdateMapper;
import com.gunitha.site_management_system_java_backend.mapper.updateToEntity.*;
import com.gunitha.site_management_system_java_backend.model.read.PersonInfo;
import com.gunitha.site_management_system_java_backend.model.update.PersonInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.IOrganisationRepository;
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
    IOrganisationRepository iOrganisationRepository;

    @Autowired
    PersonInfoUpdateEntityMapper personInfoUpdateEntityMapper;

    @Autowired
    AddressInfoUpdateEntityMapper addressInfoUpdateEntityMapper;

    @Autowired
    SiteInfoUpdateEntityMapper siteInfoUpdateEntityMapper;

    @Autowired
    OrganisationInfoUpdateEntityMapper organisationInfoUpdateEntityMapper;

    @Autowired
    LocationInfoUpdateEntityMapper locationInfoUpdateEntityMapper;

    @Override
    public PersonInfo findByPersonInfo(Long personId) {
        return personInfoReadMapper.personInfo(iPersonRepository.findById(personId).get());
    }

    @Override
    public List<PersonInfo> findPersonsByOrganisationId(Long organisationId) {
        return iPersonRepository.findPersonsByOrganisationsId(organisationId).stream()
                .map(person -> personInfoReadMapper.personInfo(person)).toList();
    }

    @Override
    public List<PersonInfo> findBySiteId(Long siteId) {
        Site site = iSiteRepository.findById(siteId).get();
        List<Person> owners1 = site.getOwners();
        List<Organisation> organisations = iOrganisationRepository.findOrganisationsBySitesId(siteId);
        List<Person> owners2 = organisations.stream().flatMap(organisation -> organisation.getSites().stream()).flatMap(site1 -> site1.getOwners().stream()).toList();
        owners2.addAll(owners1);
        return owners2.stream().map(person -> personInfoReadMapper.personInfo(person)).toList();
    }

    @Override
    public PersonInfo createPerson(PersonInfoUpdate personInfoUpdate) {
        return personInfoReadMapper.personInfo(personInfoUpdateEntityMapper.newPersonEntity(personInfoUpdate));
    }

    @Override
    public PersonInfo updatePerson(PersonInfoUpdate updatedPersonInfoUpdate) {
        PersonInfoUpdate existingPersonInfoUpdate = personInfoUpdateMapper.personInfo(iPersonRepository.findById(updatedPersonInfoUpdate.getId()).get());
        List<ChangeTargetObject> existing = ChangesUtil.getAllCustomTypeObjects(existingPersonInfoUpdate, "personInfoUpdate");
        List<ChangeTargetObject> updated = ChangesUtil.getAllCustomTypeObjects(updatedPersonInfoUpdate, "personInfoUpdate");
        List<Change> changes = ChangesUtil.getChanges(existing, updated);
        Person person = iPersonRepository.findById(updatedPersonInfoUpdate.getId()).get();
        personInfoUpdateEntityMapper.updateOrPersonNewLocation(changes, person);
        addressInfoUpdateEntityMapper.updateOrAddNewAddressEntity(changes, null, person);
        siteInfoUpdateEntityMapper.updateOrAddNewSite(changes, null);
        organisationInfoUpdateEntityMapper.updateOrAddNewOrganisation(changes, person);
        locationInfoUpdateEntityMapper.updateOrAddNewLocation(changes, null);
        iPersonRepository.save(person);
        return personInfoReadMapper.personInfo(person);
    }
}
