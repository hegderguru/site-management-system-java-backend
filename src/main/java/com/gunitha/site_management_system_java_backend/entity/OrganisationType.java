package com.gunitha.site_management_system_java_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrganisationType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "organisation_type_seq")
    @SequenceGenerator(name = "organisation_type_seq",sequenceName = "organisation_type_seq",initialValue = 100000, allocationSize = 20)
    private Long id;

    private String type;

    private String description;
    
}
