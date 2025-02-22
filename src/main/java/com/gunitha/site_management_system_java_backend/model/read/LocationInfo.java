package com.gunitha.site_management_system_java_backend.model.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo {

    private Long id;

    private Double centerLatitude;

    private Double centerLongitude;

    private List<Double[]> latitudeAndLongitudes;

}
