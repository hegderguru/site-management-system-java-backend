package com.gunitha.site_management_system_java_backend.model.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationInfo {

    private Long id;

    private String organisationType;

    private String name;

    private String description;

    private AddressInfo addressInfo;

}
