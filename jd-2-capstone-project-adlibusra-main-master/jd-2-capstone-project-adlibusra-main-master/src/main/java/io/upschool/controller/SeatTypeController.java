package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.Seat.SeatTypeResponse;
import io.upschool.dto.Seat.SeatTypeSaveRequest;
import io.upschool.service.SeatTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seatType")
@RequiredArgsConstructor

public class SeatTypeController {
    private final SeatTypeService seatTypeService;
    @PostMapping
    public ResponseEntity<BaseResponse> createSeatType(@Valid @RequestBody SeatTypeSaveRequest request){
        SeatTypeResponse save = seatTypeService.save(request);
       var response = BaseResponse.builder().status(HttpStatus.CREATED.value()).isSuccess(true).data(save).build();
return  ResponseEntity.ok(response);
    }

}
