package io.upschool.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "AIRPORTS")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "AIRPORT_NAME")
    private String airportName;

    @Column(name = "SHORTCODE_NAME")
    private String shortCodeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CİTY", nullable = false)
    private City city;


    @Column(name = "active")
    @Builder.Default()
    private Boolean active=Boolean.TRUE;

}
