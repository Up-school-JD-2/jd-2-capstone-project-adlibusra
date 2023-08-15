package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.PlaneBrand.PlaneBrandRequest;
import io.upschool.dto.PlaneBrand.PlaneBrandResponse;
import io.upschool.service.PlaneBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planeBrand")
@RequiredArgsConstructor
public class PlaneBrandConroller {

    private final PlaneBrandService brandService;

    @PostMapping
    public ResponseEntity<BaseResponse<Object>> createPlaneBrand(@RequestBody PlaneBrandRequest request){
        PlaneBrandResponse save = brandService.save(request);

        var response= BaseResponse.builder().isSuccess(true).data(save).status(HttpStatus.CREATED.value()).build();
        return ResponseEntity.ok(response);
    }

}
