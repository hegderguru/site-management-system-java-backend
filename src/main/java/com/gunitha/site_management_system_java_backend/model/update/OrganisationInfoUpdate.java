package com.gunitha.site_management_system_java_backend.model.update;

import com.gunitha.site_management_system_java_backend.change.model.ChangeTargetObjectId;
import com.gunitha.site_management_system_java_backend.model.read.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationInfoUpdate {

    @ChangeTargetObjectId
    private Long id;

    private String organisationType;

    private String name;

    private String description;

    private AddressInfo addressInfo;
}
