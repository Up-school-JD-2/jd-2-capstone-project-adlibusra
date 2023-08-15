package io.upschool.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "creditCard")
    private CreditCard  creditCard;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "passenger")
    private Passenger  passenger;

    @ManyToOne(cascade = CascadeType.PERSIST ,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "flight")
    private Flight  flight;



}
