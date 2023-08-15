package io.upschool.dto.airportDto;

import io.upschool.entity.City;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AirtportSaveRequest {

    private String airportName;
    private String shortCodeName;
    private Long cityId;
}
