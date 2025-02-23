package com.gunitha.site_management_system_java_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_seq")
    @SequenceGenerator(name = "address_seq",sequenceName = "address_seq",initialValue = 100000, allocationSize = 20)
    private Long id;

    private String floor;

    private String number;

    private String street;

    private String village;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    @OneToOne(cascade = CascadeType.REFRESH)
    private AddressType AddressType;

}
