package io.upschool.controller;


import io.upschool.dto.BaseResponse;
import io.upschool.dto.planeModelBrand.BrandMdelRequest;
import io.upschool.dto.planeModelBrand.BrandModelResponse;
import io.upschool.entity.BrandModel;
import io.upschool.service.BrandModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brandModel")
@RequiredArgsConstructor
public class BrandModelController {

    private  final BrandModelService brandModelService;
    @PostMapping
    public ResponseEntity<BrandModelResponse> createBrandMode(@RequestBody BrandMdelRequest request){
        var  BrandModelSaveResponse = brandModelService.save(request);
        return  ResponseEntity.ok(BrandModelSaveResponse);

    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<BrandModelResponse>>> getAllBrandModel() {
        List<BrandModelResponse> brandModel = brandModelService.getAllBrandModel();

        BaseResponse<List<BrandModelResponse>> response = BaseResponse.<List<BrandModelResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(brandModel)
                .build();

        return ResponseEntity.ok(response);
    }

}
