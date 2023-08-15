package io.upschool.controller;


import io.upschool.dto.BaseResponse;
import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.dto.airline.AirlineSearchRequest;
import io.upschool.dto.airportDto.AirportSearchRequest;
import io.upschool.entity.Airline;
import io.upschool.service.AirlineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;


    @GetMapping
    public ResponseEntity<List<Airline>> getAirlines(){
        var airlines= airlineService.getAirlines();
        HttpHeaders headers=new HttpHeaders();
        headers.add("HEADER","Header value  ");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(airlines);
    }
    @PostMapping
    public ResponseEntity<AirlineSaveResponse> createAirline(@Valid @RequestBody AirlineSaveRequest airlineSaveRequest){
        var response = airlineService.save(airlineSaveRequest);
        return  ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    public void deleteAirline(@PathVariable("id") Long id){
        airlineService.delete(id);
    }


    @PostMapping("/search")
    public ResponseEntity<Object> searchAirline(@RequestBody AirlineSearchRequest request) {
        Airline airlineByAirlineName = airlineService.findAirlineByAirlineName(request.getAirlineName());
        var response= BaseResponse.builder().status(HttpStatus.CREATED.value()).isSuccess(true).data(airlineByAirlineName).build();
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/search2")
    public ResponseEntity<Object> searchRoute(@Valid @RequestBody AirlineSearchRequest request) {

        List<AirlineSaveResponse>  airlines = airlineService.search(request);

        BaseResponse response = BaseResponse.<List<AirlineSaveResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airlines)
                .build();
        return ResponseEntity.ok(response);

    }
}
