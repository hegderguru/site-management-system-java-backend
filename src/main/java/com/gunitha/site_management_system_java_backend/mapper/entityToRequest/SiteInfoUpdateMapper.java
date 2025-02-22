package com.gunitha.site_management_system_java_backend.mapper.entityToRequest;


import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.read.SiteInfo;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class SiteInfoUpdateMapper {

    private final static ModelMapper siteInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Site, SiteInfoUpdate> typeMap = siteInfoModelMapper.createTypeMap(Site.class, SiteInfoUpdate.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Site::getId, SiteInfoUpdate::setId);
            mapper.map(Site::getDescription,SiteInfoUpdate::setDescription);
            mapper.map(Site::getWidth,SiteInfoUpdate::setSiteWidth);
            mapper.map(Site::getLength,SiteInfoUpdate::setLength);
        });
    }

    public SiteInfoUpdate siteInfo(Site site) {
        return siteInfoModelMapper.map(site, SiteInfoUpdate.class);
    }
}
