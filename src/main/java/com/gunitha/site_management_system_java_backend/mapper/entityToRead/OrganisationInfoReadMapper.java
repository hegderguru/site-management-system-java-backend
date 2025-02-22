package com.gunitha.site_management_system_java_backend.mapper.entityToRead;


import com.gunitha.site_management_system_java_backend.entity.Organisation;
import com.gunitha.site_management_system_java_backend.model.read.OrganisationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service("organisationInfoReadMapper")
public class OrganisationInfoReadMapper {

    private final static ModelMapper organisationInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Organisation, OrganisationInfo> typeMap = organisationInfoModelMapper.createTypeMap(Organisation.class, OrganisationInfo.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Organisation::getId, OrganisationInfo::setId);
            mapper.map(Organisation::getOrganisationType, OrganisationInfo::setOrganisationType);
            mapper.map(Organisation::getName, OrganisationInfo::setName);
            mapper.map(Organisation::getDescription, OrganisationInfo::setDescription);
        });
    }

    public OrganisationInfo personInfo(Organisation organisation) {
        return organisationInfoModelMapper.map(organisation, OrganisationInfo.class);
    }

}
