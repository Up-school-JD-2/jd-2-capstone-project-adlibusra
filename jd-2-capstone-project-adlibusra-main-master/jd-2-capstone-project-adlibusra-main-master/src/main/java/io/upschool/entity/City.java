package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CITIES", uniqueConstraints ={@UniqueConstraint(columnNames = "CITY_NM")})
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY_NM",unique = true)
    private String cityName;

    @Column(name = "DESCRIPTION")
    private String description;

}
