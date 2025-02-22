package com.gunitha.site_management_system_java_backend.mapper.entityToRequest;

import com.gunitha.site_management_system_java_backend.entity.Location;
import com.gunitha.site_management_system_java_backend.model.update.LocationInfoUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;


@Service
public class LocationInfoUpdateMapper {

    private final static ModelMapper locationInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Location, LocationInfoUpdate> typeMap = locationInfoModelMapper.createTypeMap(Location.class, LocationInfoUpdate.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Location::getId, LocationInfoUpdate::setId);
            mapper.map(Location::getCenterLatitude,LocationInfoUpdate::setCenterLatitude);
            mapper.map(Location::getCenterLongitude,LocationInfoUpdate::setCenterLongitude);
            //mapper.map(Location::getLatitudeAndLongitudes,LocationInfoUpdate::setLatitudeAndLongitudes);
        });
    }

    public LocationInfoUpdate locationInfo(Location location) {
        return locationInfoModelMapper.map(location, LocationInfoUpdate.class);
    }
}
