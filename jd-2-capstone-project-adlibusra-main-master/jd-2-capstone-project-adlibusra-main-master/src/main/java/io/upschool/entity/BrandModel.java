package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name ="planeBrandModel")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "plane_brand_id")
    private  PlaneBrand planeBrand;
}
