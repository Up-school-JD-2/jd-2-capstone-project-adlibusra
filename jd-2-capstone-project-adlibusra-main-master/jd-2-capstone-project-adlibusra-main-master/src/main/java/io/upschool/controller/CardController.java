package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.CreditCard.CardSaveRequest;
import io.upschool.dto.CreditCard.CardSaveResponse;
import io.upschool.entity.CreditCard;
import io.upschool.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/creditCard")
@RequiredArgsConstructor
public class CardController {
    private final CreditCardService cardService;

    @PostMapping
    public ResponseEntity<BaseResponse<Object>> saveCreditCard(@Valid @RequestBody CardSaveRequest request){
        CreditCard saveResponse = cardService.save(request);
        BaseResponse<Object> response = BaseResponse.builder().status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(saveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
