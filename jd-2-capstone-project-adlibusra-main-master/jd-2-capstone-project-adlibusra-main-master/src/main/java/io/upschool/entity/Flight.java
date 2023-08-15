package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.boot.convert.DurationStyle;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flights")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flightNumber",unique = true)
    private String flightNumber;

    @Column(name = "departureDate")
    private LocalDateTime departureDate;

    @Column(name = "arrivalDate")
    private LocalDateTime arrivalDate;

    //@DurationFormat(DurationStyle.ISO8601)
    @Column(name = "duration")
    private Duration duration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routes")
    private  Route route;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airlines")
    private  Airline airline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departureAirport")
    private  Airport departureAirport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrivalAirport")
    private  Airport arrivalAirport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planes")
    private  Plane plane;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flightStatus")
    private  FlightStatus flightStatus;

    @Column(name = "maxSeatNumber")
    private Long maxSeatCapacity;

    @Column(name = "price")
    private Long price;

}
