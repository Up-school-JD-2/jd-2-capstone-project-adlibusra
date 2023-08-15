package io.upschool.entity;


import io.upschool.dto.airline.AirlineSaveResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "airlines")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Airline  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AIRLINE_NM",unique = true)
    private String airlineName;

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "active")
    @Builder.Default()
    private Boolean active=Boolean.TRUE;







}
