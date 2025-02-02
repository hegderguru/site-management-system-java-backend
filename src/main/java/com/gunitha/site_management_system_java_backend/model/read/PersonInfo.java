package com.gunitha.site_management_system_java_backend.model.read;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfo {

    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    private List<AddressInfo> addressInfos;
}
