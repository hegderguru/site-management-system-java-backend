package com.gunitha.site_management_system_java_backend.model.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfo {

    private Long id;

    private String number;

    private Integer siteWidth;

    private Integer length;

    private AddressInfo addressInfo;

    private String description;

    private SiteLocation location;

}
