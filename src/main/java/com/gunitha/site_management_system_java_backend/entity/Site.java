package com.gunitha.site_management_system_java_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "site_seq")
    @SequenceGenerator(name = "site_seq",sequenceName = "site_seq",initialValue = 100000,allocationSize = 20)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;


}
