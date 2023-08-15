package io.upschool.service;

import io.upschool.dto.Flight.FlightSaveRequest;
import io.upschool.dto.Flight.FlightSaveResponse;
import io.upschool.entity.*;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.FlightRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Data
public class FlightService {
    private final FlightRepository flightRepository;
    private final RouteService routeService;
    private final AirlineService airlineService;
    private final AirportsService airportsService;
    private final PlaneService planeService;
    private final FlightStatusService statusService;


public FlightSaveResponse saveResponse(FlightSaveRequest request){
    Boolean aBoolean = flightRepository.existsByFlightNumber(request.getFlightNumber());
    if(aBoolean){
        throw new FlightAlreadySavedException("Bu veri daha önce  eklenmiş");
    }

    Flight flightSaveResponse = buildAndSave(request);

    return         FlightSaveResponse.builder()
            .flightNumber(flightSaveResponse.getFlightNumber())
            .arrivalDate(flightSaveResponse.getArrivalDate())
            .departureDate(flightSaveResponse.getDepartureDate())
            .flightStatus(flightSaveResponse.getFlightStatus().getStatusName())
            .route(flightSaveResponse.getRoute().getRouteName())
            .airline(flightSaveResponse.getAirline().getAirlineName())
            .arrivalAirport(flightSaveResponse.getArrivalAirport().getAirportName())
            .departureAirport(flightSaveResponse.getDepartureAirport().getAirportName())
            .plane(flightSaveResponse.getPlane().getName())
           .duration(flightSaveResponse.getDuration())
            .price(flightSaveResponse.getPrice())
            .build();


}

private Flight buildAndSave(FlightSaveRequest request){
   Route route = routeService.getReferenceById(request.getRoute()).orElseThrow(() -> new RuntimeException("Route bulunamadı"));
    Airline airline = airlineService.getReferenceById(request.getAirline()).orElseThrow(() -> new RuntimeException("airline bulunamadı"));
    Airport arrivalAirport = airportsService.getReferenceById(request.getArrivalAirport()).orElseThrow(() -> new RuntimeException("arrivalAirport bulunamadı"));
    Airport departureAirport = airportsService.getReferenceById(request.getDepartureAirport()).orElseThrow(() -> new RuntimeException("departureAirport bulunamadı"));
    Plane  plane = planeService.getReferenceById(request.getPlane()).orElseThrow(() -> new RuntimeException("plane bulunamadı"));
    FlightStatus flightStatus = statusService.getReferenceById(request.getFlightStatus()).orElseThrow(() -> new RuntimeException("flightStatus bulunamadı"));

    Flight newFlight= Flight.builder()
            .flightNumber(request.getFlightNumber())
            .route(route)
            .airline(airline)
            .arrivalAirport(arrivalAirport)
            .departureAirport(departureAirport)
            .plane(plane)
            .flightStatus(flightStatus)
            .departureDate(request.getDepartureDate())
            .arrivalDate(request.getArrivalDate())
            .duration(request.getDuration())
            .price(request.getPrice()).build();

   return flightRepository.save(newFlight);
}

    public List<FlightSaveResponse> getAllFlights(){


        return  flightRepository.findAll().stream().map(flight -> FlightSaveResponse.builder()
                .flightNumber(flight.getFlightNumber())
                .flightStatus(flight.getFlightStatus().getStatusName())
                .departureDate(flight.getDepartureDate())
                .arrivalDate(flight.getArrivalDate())
                .duration(flight.getDuration())
                .arrivalAirport(flight.getArrivalAirport().getAirportName())
                .departureAirport(flight.getDepartureAirport().getAirportName())
                .airline(flight.getAirline().getAirlineName())
                .plane(flight.getPlane().getName())
                .route(flight.getRoute().getRouteName()).build()).collect(Collectors.toList());
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<Flight> getReferenceById(Long id) {
        return flightRepository.findById(id);
    }


}
