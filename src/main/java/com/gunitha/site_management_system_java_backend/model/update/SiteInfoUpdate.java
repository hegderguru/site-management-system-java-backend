package com.gunitha.site_management_system_java_backend.model.update;


import com.gunitha.site_management_system_java_backend.change.model.ChangeTargetObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfoUpdate {

    @ChangeTargetObjectId
    private Long id;

    private String number;

    private Integer siteWidth;

    private Integer length;

    private String description;

    private AddressInfoUpdate addressInfo;

    private LocationInfoUpdate location;

    private List<PersonInfoUpdate> personInfoUpdates;

}
