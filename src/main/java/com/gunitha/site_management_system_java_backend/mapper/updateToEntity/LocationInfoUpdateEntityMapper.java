package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;


import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Location;
import com.gunitha.site_management_system_java_backend.model.update.LocationInfoUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LocationInfoUpdateEntityMapper {

    public void updateLocationEntity(List<Change> changes, Location location){
        changes.forEach(change -> {
            LocationInfoUpdate locationInfoUpdate = (LocationInfoUpdate) change.getRightObject();
            switch (LocationInfoUpdate.Fields.valueOf(change.getFieldName())){
                case centerLatitude -> location.setCenterLatitude(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
                case centerLongitude -> location.setCenterLongitude(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
            }
        });
    }
}
