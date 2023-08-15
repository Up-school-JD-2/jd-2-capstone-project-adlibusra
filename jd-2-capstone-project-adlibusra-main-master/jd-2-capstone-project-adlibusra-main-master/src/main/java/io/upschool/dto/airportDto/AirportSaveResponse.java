package io.upschool.dto.airportDto;


import io.upschool.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportSaveResponse {

    private String airportName;
    private String shortCodeName;
    private String city;
}
