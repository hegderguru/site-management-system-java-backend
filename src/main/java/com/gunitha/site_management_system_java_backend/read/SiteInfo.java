package com.gunitha.site_management_system_java_backend.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfo {

    private Long siteId;

    private String siteNumber;

    private List<SiteLocation> siteLocations;

    private List<PersonInfo> owners;

}
