package com.gunitha.site_management_system_java_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AddressType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_type_seq")
    @SequenceGenerator(name = "address_type_seq",sequenceName = "address_type_seq",initialValue = 100000, allocationSize = 20)
    private Long id;

    private String type;
}
