package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;

import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.update.SiteInfoUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SiteInfoUpdateEntityMapper {

    public void updateSiteEntity(List<Change> changes, Site site){
        changes.forEach(change -> {
            SiteInfoUpdate siteInfoUpdate = (SiteInfoUpdate) change.getRightObject();
            switch (SiteInfoUpdate.Fields.valueOf(change.getFieldName())){
                case number -> site.setNumber(ChangeValueExtractUtil.extractString(change.getRightValue()));
                case width -> site.setWidth(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
                case length -> site.setLength(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
                case description -> site.setDescription(ChangeValueExtractUtil.extractString(change.getRightValue()));
            }
        });
    }

}
