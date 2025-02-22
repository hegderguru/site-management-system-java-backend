package com.gunitha.site_management_system_java_backend.model.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfoUpdate {

    private Long id;

    private String number;

    private Integer siteWidth;

    private Integer length;

    private String description;

    private AddressInfoUpdate addressInfo;

    private LocationInfoUpdate location;

}
