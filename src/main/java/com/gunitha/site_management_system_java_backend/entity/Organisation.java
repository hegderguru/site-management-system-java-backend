package com.gunitha.site_management_system_java_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "organisation_seq")
    @SequenceGenerator(name = "organisation_seq",sequenceName = "organisation_seq",initialValue = 100000, allocationSize = 20)
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private OrganisationType organisationType;

    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Site> sites;
    
}
