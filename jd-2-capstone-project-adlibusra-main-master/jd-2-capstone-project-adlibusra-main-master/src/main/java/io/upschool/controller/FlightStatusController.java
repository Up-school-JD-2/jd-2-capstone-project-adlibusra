package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.FlightStatus.FlightStatusRequest;
import io.upschool.service.FlightStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flightStatus")
@RequiredArgsConstructor
public class FlightStatusController {
    private final FlightStatusService flightStatusService;
    @PostMapping
    public ResponseEntity<BaseResponse<Object>> saveStatus(@RequestBody FlightStatusRequest request){
        var  savedStatus = flightStatusService.save(request);
        var response= BaseResponse.builder().status(HttpStatus.CREATED.value()).isSuccess(true).data(savedStatus).build();
       return ResponseEntity.ok(response);
    }
}
