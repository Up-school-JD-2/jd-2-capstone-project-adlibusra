package io.upschool.dto.RouteDto;


import io.upschool.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteSaveResponse {
    private String routeName;
    private String fromAirport;
    private String toAirport;

}
