package io.upschool.dto.Seat;

import io.upschool.entity.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data

public class SeatSaveRequest {



    private String seatName;


    private Boolean isReserved;

    private Long seatType;
}
