package io.upschool.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.upschool.dto.BaseResponse;
import io.upschool.dto.airportDto.AirportSaveResponse;
import io.upschool.dto.airportDto.AirportSearchRequest;
import io.upschool.dto.airportDto.AirtportSaveRequest;
import io.upschool.dto.cityDto.CitySearchRequest;
import io.upschool.entity.Airport;
import io.upschool.service.AirportsService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequestMapping("api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportsService airportsService;

    @GetMapping
    public ResponseEntity<List<AirportSaveResponse>> getAirports(){
        List<AirportSaveResponse> allAirport = airportsService.getAllAirport();
        return  ResponseEntity.ok(allAirport);

    }
    @PostMapping
    public ResponseEntity<AirportSaveResponse> createAirport(@RequestBody AirtportSaveRequest request){
        var airportSaveResponse = airportsService.save(request);
        return ResponseEntity.ok(airportSaveResponse);

    }
    @PostMapping("/search")
    public ResponseEntity<Object> searchAirport(@RequestBody AirportSearchRequest request) {
        var airportResponse = airportsService.findAirportByAiportName(request.getAirportName());
        var response=BaseResponse.builder().status(HttpStatus.CREATED.value()).isSuccess(true).data(airportResponse).build();
        return  ResponseEntity.ok(response);

    }
    @GetMapping("/searchAirport")
    public ResponseEntity<Object> seachAirport( AirportSearchRequest searchRequest){
        var searchAirport = airportsService.searchName(searchRequest);
        BaseResponse response= BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .data(searchAirport)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }

}
