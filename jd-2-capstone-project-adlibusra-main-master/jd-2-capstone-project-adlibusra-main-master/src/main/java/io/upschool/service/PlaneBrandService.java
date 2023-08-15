package io.upschool.service;
import io.upschool.dto.PlaneBrand.PlaneBrandRequest;
import io.upschool.dto.PlaneBrand.PlaneBrandResponse;
import io.upschool.entity.City;
import io.upschool.entity.PlaneBrand;
import io.upschool.repository.PlaneBrandRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Builder
@RequiredArgsConstructor
public class PlaneBrandService {
   private final PlaneBrandRepository planeBrandRepository;

   public PlaneBrandResponse save(PlaneBrandRequest request){
       PlaneBrand newBrand = PlaneBrand.builder().name(request.getName()).build();

       PlaneBrand savedBrand = planeBrandRepository.save(newBrand);

       return  PlaneBrandResponse.builder().planeBrandName(savedBrand.getName()).build();

   }

    @Transactional(readOnly = true)
    public Optional<PlaneBrand> getReferenceById(Long id) {
        return planeBrandRepository.findById(id);
    }
}
