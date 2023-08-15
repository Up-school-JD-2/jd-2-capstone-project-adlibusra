package io.upschool.dto.Flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightSaveResponse {
    private String flightNumber;
    private String route;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String plane;
    private String flightStatus;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Duration duration;
    private  Long price;
}
