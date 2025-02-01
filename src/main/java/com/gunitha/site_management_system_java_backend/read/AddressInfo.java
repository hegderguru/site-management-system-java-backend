package com.gunitha.site_management_system_java_backend.read;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {

    private Long id;

    private String floor;

    private String number;

    private String street;

    private String village;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    private String addressType;
}
