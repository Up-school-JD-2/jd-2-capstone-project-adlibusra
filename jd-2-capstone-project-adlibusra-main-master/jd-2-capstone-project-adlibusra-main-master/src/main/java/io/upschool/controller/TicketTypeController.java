package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.TicketType.TicketTypeSaveRequest;
import io.upschool.dto.TicketType.TickettypeResponse;
import io.upschool.dto.cityDto.CitySaveResponse;
import io.upschool.service.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/ticketType")
@RequiredArgsConstructor
@RestController
public class TicketTypeController {
    private final TicketTypeService typeService;

    @PostMapping
    public ResponseEntity<BaseResponse<TickettypeResponse>> crateTicketType(@RequestBody TicketTypeSaveRequest request){
        TickettypeResponse savedTicketType = typeService.save(request);
        var build = BaseResponse.<TickettypeResponse>builder().status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(savedTicketType)
                .build();
        return ResponseEntity.ok(build);
    }
}
