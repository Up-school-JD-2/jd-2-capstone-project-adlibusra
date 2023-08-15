package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.cityDto.CitySaveRequest;
import io.upschool.dto.cityDto.CitySaveResponse;
import io.upschool.dto.cityDto.CitySearchRequest;
import io.upschool.entity.City;
import io.upschool.repository.CityRepository;
import io.upschool.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityRepository cityRepository;

    @GetMapping
    public ResponseEntity<List<City>> getAllCity(){
        List<City> allCity = cityService.getAllCity();
        return  ResponseEntity.ok(allCity);

    }
    @PostMapping
    public ResponseEntity<BaseResponse<CitySaveResponse>> saveCity(@RequestBody CitySaveRequest citySaveRequest){
        var savedCity= cityService.save(citySaveRequest);
        //return ResponseEntity.ok(savedCity);
        var response= BaseResponse.<CitySaveResponse>builder()
                .status(HttpStatus.CREATED.value()).isSuccess(true).data(savedCity).build();
        return  ResponseEntity.ok(response);


    }

    @GetMapping("/search")
    public ResponseEntity<Object> seachCity( CitySearchRequest searchRequest){
        var searchCity = cityService.search(searchRequest);
BaseResponse response= BaseResponse.builder()
        .status(HttpStatus.OK.value())
        .data(searchCity)
        .isSuccess(true)
        .build();
return ResponseEntity.ok(response);
    }

}
