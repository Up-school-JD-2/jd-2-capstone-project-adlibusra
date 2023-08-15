package io.upschool.dto.Ticket;


import io.upschool.dto.CreditCard.CardSaveRequest;
import io.upschool.dto.Passenger.PassengerSaveRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSaveRequest {
    @NotBlank

    private String ticketNumber;

    @Valid
    private PassengerSaveRequest passengerSaveRequest;
    private Long flightId;
    private Long ticketTypeName;

    @Valid
    private CardSaveRequest cardSaveRequest;

    private LocalDateTime ticketIssuedMoment;

}
