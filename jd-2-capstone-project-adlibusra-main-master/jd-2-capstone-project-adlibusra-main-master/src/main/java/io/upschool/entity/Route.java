package io.upschool.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "routeName")
    private String routeName;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromAirport", nullable = false)

    private Airport fromAirport;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toAirport", nullable = false)

    private Airport toAirport;


}
