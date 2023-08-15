package io.upschool.dto.plane;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaneSaveResponse {

    private String name;
    private Long seatNumber;
    private String planeBrand;

}
