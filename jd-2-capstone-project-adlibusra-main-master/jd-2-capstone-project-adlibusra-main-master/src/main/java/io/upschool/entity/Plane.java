package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "planes")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plane_name",unique = true)

    @Basic(optional = false)
    private String name;

    @Column(name = "seatNumber")
    private Long  seatNumber;


    @Column(name = "active")
    @Builder.Default()
    private Boolean active=Boolean.TRUE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planeBrand")
    private PlaneBrand  planeBrand;

}
