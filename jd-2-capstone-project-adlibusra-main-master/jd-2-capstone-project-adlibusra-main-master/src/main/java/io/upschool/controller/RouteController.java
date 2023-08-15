package io.upschool.controller;


import io.upschool.dto.BaseResponse;
import io.upschool.dto.RouteDto.RouteSaveRequest;
import io.upschool.dto.RouteDto.RouteSaveResponse;
import io.upschool.dto.RouteDto.RouteSearchRequest;
import io.upschool.entity.Route;
import io.upschool.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;



    @GetMapping
    public ResponseEntity<List<RouteSaveResponse>> getRoutes(){
        List<RouteSaveResponse> allRoutes=routeService.getAllAllRoute();
        return ResponseEntity.ok(allRoutes);
    }

    @PostMapping
    public ResponseEntity<Route> createResponse(@RequestBody RouteSaveRequest request){
        var routeSaveResponse = routeService.save(request);
        return  ResponseEntity.ok(routeSaveResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteSaveResponse> searchResponse(@PathVariable Long id){
        var routeSaveResponse = routeService.searchRoute(id);
        return ResponseEntity.ok(routeSaveResponse);
    }

    @GetMapping(value= "/search")
    public ResponseEntity<Object> searchRoute(@Valid @RequestBody RouteSearchRequest request) {

        List<RouteSaveResponse> routes = routeService.search(request);

        BaseResponse response = BaseResponse.<List<RouteSaveResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(routes)
                .build();
        return ResponseEntity.ok(response);

    }
}
