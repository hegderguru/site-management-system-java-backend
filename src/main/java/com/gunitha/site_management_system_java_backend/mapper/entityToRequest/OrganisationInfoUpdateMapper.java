package com.gunitha.site_management_system_java_backend.mapper.entityToRequest;


import com.gunitha.site_management_system_java_backend.entity.Organisation;
import com.gunitha.site_management_system_java_backend.model.read.OrganisationInfo;
import com.gunitha.site_management_system_java_backend.model.update.OrganisationInfoUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class OrganisationInfoUpdateMapper {

    private final static ModelMapper organisationInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Organisation, OrganisationInfoUpdate> typeMap = organisationInfoModelMapper.createTypeMap(Organisation.class, OrganisationInfoUpdate.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Organisation::getId, OrganisationInfoUpdate::setId);
            mapper.map(Organisation::getOrganisationType, OrganisationInfoUpdate::setOrganisationType);
            mapper.map(Organisation::getName, OrganisationInfoUpdate::setName);
            mapper.map(Organisation::getDescription, OrganisationInfoUpdate::setDescription);
        });
    }

    public OrganisationInfoUpdate personInfo(Organisation organisation) {
        return organisationInfoModelMapper.map(organisation, OrganisationInfoUpdate.class);
    }

}
