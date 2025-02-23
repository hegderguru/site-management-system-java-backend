package com.gunitha.site_management_system_java_backend.model.update;


import com.gunitha.site_management_system_java_backend.change.model.ChangeTargetObjectId;
import com.gunitha.site_management_system_java_backend.model.read.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants(asEnum = true)
public class PersonInfoUpdate {

    @ChangeTargetObjectId
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDateTime dateOfBirth;

    private String gender;

    private List<AddressInfo> addressInfos;
}
