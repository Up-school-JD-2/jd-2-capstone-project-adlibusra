package io.upschool.service;

import io.upschool.dto.airportDto.AirportSaveResponse;
import io.upschool.dto.plane.PlaneSaveRequest;
import io.upschool.dto.plane.PlaneSaveResponse;
import io.upschool.entity.City;
import io.upschool.entity.Plane;
import io.upschool.entity.PlaneBrand;
import io.upschool.entity.Route;
import io.upschool.repository.PlaneRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class PlaneService {

    private final PlaneRepository planeRepository;
    private final PlaneBrandService brandService;


    public PlaneSaveResponse save(PlaneSaveRequest request){
        Optional<PlaneBrand> planeBrand = brandService.getReferenceById(request.getPlaneBrandId());

        var newPlane = Plane.builder()
                .name(request.getName())
                .seatNumber(request.getSeatNumber()).planeBrand(planeBrand.get()).build();
        Plane savedPlane = planeRepository.save(newPlane);
        return PlaneSaveResponse.builder()
                .name(newPlane.getName())
                .seatNumber(newPlane.getSeatNumber())
                .planeBrand(newPlane.getPlaneBrand().getName()).build();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<Plane> getReferenceById(Long id) {
        return planeRepository.findById(id);
    }

  @Transactional
   public List<Plane> findByNameIgnoreCase(String name) {
       List<Plane> response = planeRepository.findByNameContainingIgnoreCase(name);
        return response;
    }

    public List<PlaneSaveResponse> getAllPlane(){
        return  planeRepository.findAll().stream().map(plane -> PlaneSaveResponse.builder()
                .name(plane.getName())
                .planeBrand(plane.getPlaneBrand().getName()).build()).collect(Collectors.toList());

    }


    public List<Plane> getAllCity() {
        return planeRepository.findAll();
    }
}
