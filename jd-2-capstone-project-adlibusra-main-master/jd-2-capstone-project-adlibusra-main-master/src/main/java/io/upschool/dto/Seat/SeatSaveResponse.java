package io.upschool.dto.Seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class SeatSaveResponse {

    private String seatName;


    private Boolean isReserved;

    private String seatType;
}
