package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.Ticket.TicketSaveRequest;
import io.upschool.dto.Ticket.TicketSaveResponse;
import io.upschool.dto.Ticket.TicketSearchRequest;
import io.upschool.dto.cityDto.CitySearchRequest;
import io.upschool.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    @PostMapping
    public ResponseEntity<BaseResponse<TicketSaveResponse>> createTciket(@RequestBody TicketSaveRequest request){
        var savedTicket = ticketService.save(request);
        var response=BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(savedTicket)
                .isSuccess(true).build();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/search")
    public ResponseEntity<Object> seachTicket( TicketSearchRequest searchRequest){
        var searchTicket = ticketService.searchTicketNumber(searchRequest);
        BaseResponse response= BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .data(searchTicket)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("id") Long id){

        ticketService.delete(id);
        BaseResponse response= BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .data("Bilet iptal edilmiştir.")
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }
}
