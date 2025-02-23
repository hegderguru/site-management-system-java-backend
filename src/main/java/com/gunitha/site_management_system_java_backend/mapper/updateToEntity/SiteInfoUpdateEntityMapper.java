package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;

import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Location;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.update.LocationInfoUpdate;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SiteInfoUpdateEntityMapper {

    public List<Site> updateOrAddNewSite(List<Change> changes, Site site) {
        Map<SiteInfoUpdate, List<Change>> siteInfoUpdateMap = changes.stream().filter(change -> Objects.nonNull(change.getLeftObject()) && change.getRightObject() instanceof SiteInfoUpdate)
                .collect(Collectors.groupingBy(change -> (SiteInfoUpdate) change.getRightObject()));
        siteInfoUpdateMap.keySet().forEach(key -> updateSiteEntity(siteInfoUpdateMap.get(key), getLocation(key, site)));

        return changes.stream().filter(change -> Objects.isNull(change.getLeftObject()) && change.getRightObject() instanceof LocationInfoUpdate)
                .map(change -> newSiteEntity((SiteInfoUpdate) change.getRightObject())).toList();
    }

    private Site getLocation(SiteInfoUpdate siteInfoUpdate, Site site) {
        if (Objects.nonNull(site) && site.getId().equals(siteInfoUpdate.getId())) {
            return site;
        }
        return null;
    }


    public void updateSiteEntity(List<Change> changes, Site site) {
        changes.forEach(change -> {
            SiteInfoUpdate siteInfoUpdate = (SiteInfoUpdate) change.getRightObject();
            switch (SiteInfoUpdate.Fields.valueOf(change.getFieldName())) {
                case number -> site.setNumber(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case width -> site.setWidth(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
                case length -> site.setLength(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
                case description -> site.setDescription(ChangeValueExtractUtil.extractString(change.getRightValue()));
            }
        });
    }

    private Site newSiteEntity(SiteInfoUpdate siteInfoUpdate) {
        return Site.builder()
                .number(siteInfoUpdate.getNumber())
                .description(siteInfoUpdate.getDescription())
                .width(siteInfoUpdate.getWidth())
                .length(siteInfoUpdate.getLength())
                .build();
    }

}
