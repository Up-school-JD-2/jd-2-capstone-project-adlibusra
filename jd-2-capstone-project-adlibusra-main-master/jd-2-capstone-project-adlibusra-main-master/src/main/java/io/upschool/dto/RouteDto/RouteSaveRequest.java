package io.upschool.dto.RouteDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RouteSaveRequest {
    private String routeName;
    private Long fromAirport;
    private Long toAirport;
}
