package com.gunitha.site_management_system_java_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "site_seq")
    @SequenceGenerator(name = "site_seq",sequenceName = "site_seq",initialValue = 100000,allocationSize = 20)
    private Long id;

    private String number;

    private Double width;

    private Double length;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Address siteAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Person> owners;

}
