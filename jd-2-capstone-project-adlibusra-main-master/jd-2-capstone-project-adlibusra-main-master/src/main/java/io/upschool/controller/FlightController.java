package io.upschool.controller;


import io.upschool.dto.BaseResponse;
import io.upschool.dto.Flight.FlightSaveRequest;
import io.upschool.dto.Flight.FlightSaveResponse;
import io.upschool.dto.airportDto.AirportSaveResponse;
import io.upschool.dto.airportDto.AirtportSaveRequest;
import io.upschool.dto.plane.PlaneSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Plane;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @PostMapping
    public ResponseEntity<FlightSaveResponse> createAirport(@Valid @RequestBody FlightSaveRequest request){
        var flightSaveResponse = flightService.saveResponse(request);
        return ResponseEntity.ok(flightSaveResponse);

    }
    @GetMapping
    public ResponseEntity<List<FlightSaveResponse>> getAllFlights(){
        List<FlightSaveResponse> allFlight = flightService.getAllFlights();
        return ResponseEntity.ok(allFlight);
    }


 }
