package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.FlightStatus.FlightStatusRequest;
import io.upschool.dto.Passenger.PassengerSaveRequest;
import io.upschool.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private  final PassengerService passengerService;
    @PostMapping
    public ResponseEntity<BaseResponse<Object>> saveStatus(@Valid @RequestBody PassengerSaveRequest request){
        var  savedPassenger = passengerService.savePassenger(request);
        var response= BaseResponse.builder().status(HttpStatus.CREATED.value()).isSuccess(true).data(savedPassenger).build();
        return ResponseEntity.ok(response);
    }
}
