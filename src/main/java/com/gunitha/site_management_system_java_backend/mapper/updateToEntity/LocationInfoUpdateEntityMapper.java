package com.gunitha.site_management_system_java_backend.mapper.updateToEntity;


import com.gunitha.site_management_system_java_backend.change.model.Change;
import com.gunitha.site_management_system_java_backend.change.model.ChangeValueExtractUtil;
import com.gunitha.site_management_system_java_backend.entity.Address;
import com.gunitha.site_management_system_java_backend.entity.Location;
import com.gunitha.site_management_system_java_backend.entity.Person;
import com.gunitha.site_management_system_java_backend.entity.Site;
import com.gunitha.site_management_system_java_backend.model.update.AddressInfoUpdate;
import com.gunitha.site_management_system_java_backend.model.update.LocationInfoUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LocationInfoUpdateEntityMapper {

    public List<Location> updateOrAddNewLocation(List<Change> changes, Site site) {
        Map<LocationInfoUpdate, List<Change>> locationUpdatesMap = changes.stream().filter(change -> Objects.nonNull(change.getLeftObject()) && change.getRightObject() instanceof LocationInfoUpdate)
                .collect(Collectors.groupingBy(change -> (LocationInfoUpdate) change.getRightObject()));
        locationUpdatesMap.keySet().forEach(key -> updateLocationEntity(locationUpdatesMap.get(key), getLocation(key, site)));

        return changes.stream().filter(change -> Objects.isNull(change.getLeftObject()) && change.getRightObject() instanceof LocationInfoUpdate)
                .map(change -> newLocationEntity((LocationInfoUpdate) change.getRightObject())).toList();
    }

    private Location getLocation(LocationInfoUpdate locationInfoUpdate, Site site) {
        if (Objects.nonNull(site) && site.getSiteAddress().getId().equals(locationInfoUpdate.getId())) {
            return site.getLocation();
        }
        return null;
    }

    public void updateLocationEntity(List<Change> changes, Location location) {
        changes.forEach(change -> {
            LocationInfoUpdate locationInfoUpdate = (LocationInfoUpdate) change.getRightObject();
            switch (LocationInfoUpdate.Fields.valueOf(change.getFieldName())) {
                case centerLatitude ->
                        location.setCenterLatitude(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
                case centerLongitude ->
                        location.setCenterLongitude(ChangeValueExtractUtil.extractDouble(change.getRightValue()));
            }
        });
    }

    private Location newLocationEntity(LocationInfoUpdate locationInfoUpdate) {
        return Location.builder()
                .centerLatitude(locationInfoUpdate.getCenterLatitude())
                .centerLongitude(locationInfoUpdate.getCenterLongitude())
                .build();
    }
}
