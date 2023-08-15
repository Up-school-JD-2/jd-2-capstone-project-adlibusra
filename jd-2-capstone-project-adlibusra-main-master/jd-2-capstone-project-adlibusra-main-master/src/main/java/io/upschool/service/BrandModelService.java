package io.upschool.service;

import io.upschool.dto.Flight.FlightSaveResponse;
import io.upschool.dto.planeModelBrand.BrandMdelRequest;
import io.upschool.dto.planeModelBrand.BrandModelResponse;
import io.upschool.entity.BrandModel;
import io.upschool.entity.PlaneBrand;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.BrandModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandModelService {
    private final BrandModelRepository brandModelRepository;
    private  final PlaneBrandService brandService;

    public BrandModelResponse save(BrandMdelRequest request){
        BrandModel findByName=brandModelRepository.findByNameIgnoreCase(request.getName());
        if(findByName!=null){
            throw new FlightAlreadySavedException("Bu ver daha önce eklenmiş");
        }
        Optional<PlaneBrand> planeBrand = brandService.getReferenceById(request.getPlaneBrand());

        BrandModel newBrandModel= BrandModel.builder().planeBrand(planeBrand.get()).name(request.getName()).build();
        BrandModel savebBrandModel= brandModelRepository.save(newBrandModel);

        return BrandModelResponse.builder().brandModelName(newBrandModel.getName()).brandModelName(savebBrandModel.getPlaneBrand().getName()).build();



    }
    public List<BrandModelResponse> getAllBrandModel() {

        return  brandModelRepository.findAll().stream().map(brandModel -> BrandModelResponse.builder()
                .brandModelName(brandModel.getName()).brandName(brandModel.getName()).build()).collect(Collectors.toUnmodifiableList());

    }
}
