package com.gunitha.site_management_system_java_backend.mapper.entityToRead;


import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.read.SiteInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class SiteInfoReadMapper {

    private final static ModelMapper siteInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Site, SiteInfo> typeMap = siteInfoModelMapper.createTypeMap(Site.class, SiteInfo.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Site::getId, SiteInfo::setId);
            mapper.map(Site::getDescription,SiteInfo::setDescription);
            mapper.map(Site::getWidth,SiteInfo::setSiteWidth);
            mapper.map(Site::getLength,SiteInfo::setLength);
        });
    }

    public SiteInfo siteInfo(Site site) {
        return siteInfoModelMapper.map(site, SiteInfo.class);
    }
}
