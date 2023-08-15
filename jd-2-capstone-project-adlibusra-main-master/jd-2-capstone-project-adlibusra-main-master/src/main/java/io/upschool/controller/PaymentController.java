package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.Payment.PaymentSaveRequest;
import io.upschool.service.PaymentService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@Data
@Builder
@RequiredArgsConstructor


public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<BaseResponse<Object>> createPayment(@Valid @RequestBody PaymentSaveRequest request){
        var save = paymentService.save(request);
        var response = BaseResponse.builder().isSuccess(true)
                .data(save)
                .status(HttpStatus.CREATED.value())
                .build();
        return  ResponseEntity.ok(response);


    }
}
