package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;


import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Organisation;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.model.update.OrganisationInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.OrganisationTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrganisationInfoUpdateEntityMapper {

    @Autowired
    OrganisationTypeRepository organisationTypeRepository;

    public List<Organisation> updateOrAddNewLocation(List<Change> changes, Person person) {
        Map<OrganisationInfoUpdate, List<Change>> organisationInfoUpdateMap = changes.stream().filter(change -> Objects.nonNull(change.getLeftObject()) && change.getRightObject() instanceof OrganisationInfoUpdate)
                .collect(Collectors.groupingBy(change -> (OrganisationInfoUpdate) change.getRightObject()));
        organisationInfoUpdateMap.keySet().forEach(key -> updateOrganisationEntity(organisationInfoUpdateMap.get(key), getOrganisation(key, person)));

        return changes.stream().filter(change -> Objects.isNull(change.getLeftObject()) && change.getRightObject() instanceof OrganisationInfoUpdate)
                .map(change -> newOrganisationEntity((OrganisationInfoUpdate) change.getRightObject())).toList();
    }

    private Organisation getOrganisation(OrganisationInfoUpdate organisationInfoUpdate, Person person) {
        if (Objects.nonNull(person)) {
            Optional<Organisation> organisationOptional = person.getOrganisations().stream().filter(organisation -> organisation.getId().equals(organisationInfoUpdate.getId())).findFirst();
            return organisationOptional.orElse(null);
        }
        return null;
    }

    public void updateOrganisationEntity(List<Change> changes, Organisation organisation) {
        changes.forEach(change -> {
            switch (OrganisationInfoUpdate.Fields.valueOf(change.getFieldName())) {
                case name -> organisation.setName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case description ->
                        organisation.setDescription(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case organisationType ->
                        organisation.setOrganisationType(organisationTypeRepository.findByOrganisationType(ChangeValueExtractUtil.extractString(change.getRightValue())).get());
            }
        });
    }

    private Organisation newOrganisationEntity(OrganisationInfoUpdate organisationInfoUpdate) {
        return Organisation.builder()
                .name(organisationInfoUpdate.getName())
                .description(organisationInfoUpdate.getDescription())
                .organisationType(organisationTypeRepository.findByOrganisationType(organisationInfoUpdate.getOrganisationType()).get())
                .build();
    }

}
