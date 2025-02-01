package com.gunitha.site_management_system_java_backend.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<PersonInfo> owners;

}
