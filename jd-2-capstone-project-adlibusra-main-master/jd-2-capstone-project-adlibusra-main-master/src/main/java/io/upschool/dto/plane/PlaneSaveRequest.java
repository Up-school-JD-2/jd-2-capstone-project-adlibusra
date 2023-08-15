package io.upschool.dto.plane;

import io.upschool.entity.PlaneBrand;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaneSaveRequest {


    private String name;
    private Long  seatNumber;

    private Long planeBrandId;
}
