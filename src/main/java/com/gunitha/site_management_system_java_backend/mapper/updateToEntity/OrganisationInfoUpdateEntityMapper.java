package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;


import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Organisation;
import com.gunitha.site_management_system_java_backend.model.update.OrganisationInfoUpdate;
import com.gunitha.site_management_system_java_backend.repository.OrganisationTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrganisationInfoUpdateEntityMapper {

    @Autowired
    OrganisationTypeRepository organisationTypeRepository;

    public void updateOrganisationEntity(List<Change> changes, Organisation organisation) {
        changes.forEach(change -> {
            OrganisationInfoUpdate organisationInfoUpdate = (OrganisationInfoUpdate) change.getRightObject();
            switch (OrganisationInfoUpdate.Fields.valueOf(change.getFieldName())) {
                case name -> organisation.setName(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case description ->
                        organisation.setDescription(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case organisationType ->
                        organisation.setOrganisationType(organisationTypeRepository.findByOrganisationType(ChangeValueExtractUtil.extractString(change.getRightValue())).get());
            }
        });
    }

}
