package com.gunitha.site_management_system_java_backend.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "location_seq")
    @SequenceGenerator(name = "location_seq",sequenceName = "location_seq",initialValue = 100000, allocationSize = 20)
    private Long id;

   /* @Column(columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<Double> latitudeAndLongitudes;
*/
    private Double centerLatitude;

    private Double centerLongitude;

}
