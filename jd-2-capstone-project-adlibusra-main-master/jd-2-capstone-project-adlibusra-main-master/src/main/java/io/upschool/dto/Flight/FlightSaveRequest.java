package io.upschool.dto.Flight;




import lombok.*;


import java.time.Duration;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FlightSaveRequest {
    private String flightNumber;
    private Long route;
    private Long airline;
    private Long departureAirport;
    private Long arrivalAirport;
    private Long plane;
    private Long flightStatus;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Duration duration;
    private  Long price;

}