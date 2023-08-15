package io.upschool.dto.planeModelBrand;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandModelResponse {
    private String brandName;
    private String brandModelName;

}
