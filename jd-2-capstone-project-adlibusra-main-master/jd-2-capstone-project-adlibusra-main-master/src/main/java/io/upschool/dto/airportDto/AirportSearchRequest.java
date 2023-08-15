package io.upschool.dto.airportDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AirportSearchRequest {

    private String airportName;
    private String shortCodeName;
}
