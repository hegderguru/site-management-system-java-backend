package com.gunitha.site_management_system_java_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "person_seq")
    @SequenceGenerator(name = "person_seq",sequenceName = "person_seq",initialValue = 100000,allocationSize = 20)
    private Long id;

    private String firstName;

    private String middleName;

    private String LastName;

    private LocalDateTime dateOfBirth;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Organisation> organisations;

}
