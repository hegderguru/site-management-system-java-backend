package com.gunitha.site_management_system_java_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gender_seq")
    @SequenceGenerator(name = "gender_seq",sequenceName = "gender_seq",initialValue = 100000,allocationSize = 20)
    private Long id;

    private String type;

    private String description;

}
