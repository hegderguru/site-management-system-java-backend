package com.gunitha.site_management_system_java_backend.mapper.entityToRead;

import com.gunitha.site_management_system_java_backend.entity.Location;
import com.gunitha.site_management_system_java_backend.model.read.LocationInfo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Data
@Service
public class LocationInfoReadMapper {

    private final static ModelMapper locationInfoModelMapper = new ModelMapper();

    static {
        TypeMap<Location, LocationInfo> typeMap = locationInfoModelMapper.createTypeMap(Location.class, LocationInfo.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Location::getId, LocationInfo::setId);
            mapper.map(Location::getCenterLatitude,LocationInfo::setCenterLatitude);
            mapper.map(Location::getCenterLongitude,LocationInfo::setCenterLongitude);
            mapper.map(Location::getLatitudeAndLongitudes,LocationInfo::setLatitudeAndLongitudes);
        });
    }

    public LocationInfo locationInfo(Location location) {
        return locationInfoModelMapper.map(location, LocationInfo.class);
    }
}
