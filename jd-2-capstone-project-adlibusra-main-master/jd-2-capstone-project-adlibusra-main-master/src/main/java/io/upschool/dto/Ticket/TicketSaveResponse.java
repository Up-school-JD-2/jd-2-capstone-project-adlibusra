package io.upschool.dto.Ticket;

import io.upschool.dto.CreditCard.CardSaveResponse;
import io.upschool.dto.Passenger.PassengerSaveResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSaveResponse {

    private String ticketNumber;

    private Long flightPrice;
    private String ticketTypeName;
    private LocalDateTime ticketIssuedMoment;
    private CardSaveResponse cardSaveResponse;
    private PassengerSaveResponse passengerSaveResponse;

}
