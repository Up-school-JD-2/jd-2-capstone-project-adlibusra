package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Table(name = "tickets")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticketNumber" , unique = true , nullable = false,length = 100)

    private String ticketNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight", nullable = false)
    private  Flight flight;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticketTypeName", nullable = false)
    private  TicketType ticketTypeName;
    @Column(name = "ticket_issued_moment")
    private LocalDateTime ticketIssuedMoment;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "creditCard")
    private CreditCard  creditCard;
   // @ManyToOne(fetch = FetchType.EAGER)
  //  @JoinColumn(name = "payment", nullable = false)
   // private Payment payment;

}
