package io.upschool.dto.RouteDto;

import io.upschool.dto.airline.AirlineSearchRequest;
import io.upschool.dto.airportDto.AirportSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RouteSearchRequest {

    private String routeName;

    private AirportSearchRequest fromAirport ;

    private AirportSearchRequest toAirport ;
}
