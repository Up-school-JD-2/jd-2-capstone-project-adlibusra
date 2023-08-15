package io.upschool.controller;


import io.upschool.dto.plane.PlaneSaveRequest;
import io.upschool.dto.plane.PlaneSaveResponse;
import io.upschool.entity.City;
import io.upschool.entity.Plane;
import io.upschool.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping
    public ResponseEntity<PlaneSaveResponse> createPlane(@RequestBody PlaneSaveRequest request){
        PlaneSaveResponse save = planeService.save(request);
        return  ResponseEntity.ok(save);

    }

    @GetMapping
    public ResponseEntity<List<PlaneSaveResponse>> getAllPlanes(){
        List<PlaneSaveResponse> allPlane = planeService.getAllPlane();
return ResponseEntity.ok(allPlane);
    }


    @GetMapping("{name}")
    public ResponseEntity<List<Plane>> getPlanes(@PathVariable String name) {
        List<Plane> response = (List<Plane>) planeService.findByNameIgnoreCase(name);
        return ResponseEntity.ok(response);
    }
}
