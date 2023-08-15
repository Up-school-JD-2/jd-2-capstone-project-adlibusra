package io.upschool.service;

import io.upschool.dto.RouteDto.RouteSaveRequest;
import io.upschool.dto.RouteDto.RouteSaveResponse;
import io.upschool.dto.RouteDto.RouteSearchRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlredySaved;
import io.upschool.repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
@Transactional
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final AirportsService airportsService;

    public Route save(RouteSaveRequest routeSaveRequest) {
        int routeCountByName = routeRepository.findRouteCountName(routeSaveRequest.getRouteName());
        if (routeCountByName > 0) {
            throw new RouteAlredySaved("Bu veri daha önce eklenmiş");
        }
        Optional<Airport> fromAirport = airportsService.getReferenceById(routeSaveRequest.getFromAirport());
        Optional<Airport> referenceById = airportsService.getReferenceById(routeSaveRequest.getToAirport());


        Route newRoute = Route.builder().routeName(routeSaveRequest.getRouteName())
                .fromAirport(fromAirport.get())
                .toAirport(referenceById.get()).build();
        return routeRepository.save(newRoute);
    }

    public List<RouteSaveResponse> getAllAllRoute() {

        return routeRepository.findAll().stream().map(route ->
                RouteSaveResponse.builder()
                        .routeName(route.getRouteName())
                        .toAirport(route.getToAirport().getAirportName())
                        .fromAirport(route.getFromAirport().getAirportName()).build()).collect(Collectors.toList());
    }

    public RouteSaveResponse searchRoute(Long id) {
        var optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isPresent()) {
            var route = optionalRoute.get();
            return RouteSaveResponse.builder().routeName(route.getRouteName())
                    .fromAirport(route.getFromAirport().getAirportName())
                    .toAirport(route.getToAirport().getAirportName()).build();
        }
        throw new RuntimeException("Route not found");
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<Route> getReferenceById(Long id) {
        return routeRepository.findById(id);
    }
    public List<RouteSaveResponse> search(RouteSearchRequest request) {

        var routeBuilder = Route.builder();

        if (request.getFromAirport() != null) {
            Airport departureAirport = Airport.builder()
                    .airportName(request.getFromAirport().getAirportName())
                    .build();
            routeBuilder.fromAirport(departureAirport);
        }
        if (request.getToAirport() != null) {
            Airport arrivalAirport = Airport.builder()
                    .airportName(request.getToAirport().getAirportName())
                    .build();
            routeBuilder.toAirport(arrivalAirport);
        }

        if (request.getRouteName()!= null) {
            routeBuilder.routeName(request.getRouteName());
        }
        Route route = routeBuilder.build();

        Example<Route> routeExample = Example.of(route,
                ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<Route> routes = routeRepository.findAll(routeExample);
        return routes.stream().map(r -> entityResponse(r)).toList();
    }

    public RouteSaveResponse entityResponse(Route route){

        return  RouteSaveResponse.builder()
                .routeName(route.getRouteName())
                .toAirport(route.getToAirport().getAirportName())
                .fromAirport(route.getFromAirport().getAirportName())
                .build();

    }

}
