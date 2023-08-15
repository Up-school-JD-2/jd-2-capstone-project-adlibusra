package io.upschool.service;

import io.upschool.dto.cityDto.CitySaveRequest;
import io.upschool.dto.cityDto.CitySaveResponse;
import io.upschool.dto.cityDto.CitySearchRequest;
import io.upschool.entity.City;
import io.upschool.exception.CityAlreadySavedException;
import io.upschool.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public CitySaveResponse save(CitySaveRequest citySaveRequest){
        int cityCountByName = cityRepository.findCityCountByName(citySaveRequest.getCityName());
        if(cityCountByName > 0){
            throw new CityAlreadySavedException("Bu veri daha önce  eklenmiş");
        }

         var newCity= City.builder()
                 .cityName(citySaveRequest.getCityName())
                         .description(citySaveRequest.getDescription()).build();
        City savedCity = cityRepository.save(newCity);
        return  CitySaveResponse.builder().cityName(savedCity.getCityName()).description(savedCity.getDescription()).build();


    }

    @Transactional(readOnly = true)
    public Optional<City> getReferenceById(Long id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }


    public List<CitySaveResponse> search(CitySearchRequest searchRequest) {

        City city = City.builder()
                .cityName(searchRequest.getCityName())
                .build();
        Example<City> cityExample=Example.of(city,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        List<City> cities = cityRepository.findAll(cityExample);
        return  cities.stream().map(city1 ->entityResponse(city1)).toList();


    }

    public CitySaveResponse entityResponse(City city){

        return  CitySaveResponse.builder().cityName(city.getCityName())
                .description(city.getDescription()).build();
    }
}
