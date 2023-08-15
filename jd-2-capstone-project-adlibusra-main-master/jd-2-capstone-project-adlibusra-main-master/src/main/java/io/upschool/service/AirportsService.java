package io.upschool.service;

import io.upschool.dto.airportDto.AirportSaveResponse;
import io.upschool.dto.airportDto.AirportSearchRequest;
import io.upschool.dto.airportDto.AirtportSaveRequest;
import io.upschool.dto.cityDto.CitySaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.City;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.exception.CityNotFountOrPassiveException;
import io.upschool.exception.PassengerAlreadySavedException;
import io.upschool.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportsService {
   private final  AirportRepository airportRepository;
   private final CityService cityService;

   public AirportSaveResponse save(AirtportSaveRequest airtportSaveRequest){

      Boolean aBoolean=airportRepository.existsByAirportName(airtportSaveRequest.getAirportName());
      if(aBoolean){
         throw new AirportAlreadySavedException("Bu havaalanı daha önce eklenmiş");
      }
      Airport airportSaveResponse = buildAirportAndSave(airtportSaveRequest);
      return  AirportSaveResponse.builder()
              .airportName(airportSaveResponse.getAirportName())
              .shortCodeName(airportSaveResponse.getShortCodeName()).build();
   }

   private Airport buildAirportAndSave(AirtportSaveRequest request){
      Optional<City> referenceCityId = cityService.getReferenceById(request.getCityId());
if(!referenceCityId.isPresent()){
   throw  new CityNotFountOrPassiveException("City not fount");
}

   Airport newAirport= Airport.builder()
           .airportName(request.getAirportName())
           .shortCodeName(request.getShortCodeName())
           .city(referenceCityId.get()).build();
   return  airportRepository.save(newAirport);

   }

   @org.springframework.transaction.annotation.Transactional(readOnly = true)
   public Optional<Airport> getReferenceById(Long id) {
      return airportRepository.findById(id);
   }

public List<AirportSaveResponse> getAllAirport(){


      return  airportRepository.findAll().stream().map(airport -> AirportSaveResponse.builder()
              .airportName(airport.getAirportName())
              .shortCodeName(airport.getShortCodeName())
              .city(airport.getCity().getCityName())
              .build()).collect(Collectors.toList());
}



   public AirportSaveResponse findAirportByAiportName(String AirportName){

      return  getAllAirport().stream().filter(airport -> airport.getAirportName().equals(AirportName)).findFirst().orElse(null);
   }

   public List<AirportSaveResponse> searchName(AirportSearchRequest searchRequest){
      Airport airport=Airport.builder()
              .airportName(searchRequest.getAirportName())
              .shortCodeName(searchRequest.getShortCodeName())
              .build();
      Example<Airport> airportExample = Example.of(airport,
              ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
      List<Airport> airportList = airportRepository.findAll(airportExample);
      return  airportList.stream().map(airport1-> entityResponse(airport1)).toList();

   }
   public AirportSaveResponse entityResponse(Airport airport){

      return  AirportSaveResponse.builder()
              .airportName(airport.getAirportName())
              .shortCodeName(airport.getShortCodeName())
              .build();
   }
}

