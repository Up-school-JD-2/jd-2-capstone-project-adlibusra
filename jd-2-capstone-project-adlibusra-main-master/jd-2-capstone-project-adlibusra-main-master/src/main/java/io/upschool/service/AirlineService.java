package io.upschool.service;

import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.dto.airline.AirlineSearchRequest;
import io.upschool.entity.Airline;
import io.upschool.repository.AirlineRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineSaveResponse save(AirlineSaveRequest request){
        var newairline= Airline.builder().airlineName(request.getAirlineName()).build();

        Airline savedAirline= airlineRepository.save(newairline);

        return  AirlineSaveResponse.builder()
                .name(savedAirline.getAirlineName() )
                .id(savedAirline.getId()).is_active(true).build();

    }

    public List<Airline> getAirlines() {

        return airlineRepository.findAll();
    }

    public void delete(Long id){
        var airline = airlineRepository.findById(id).get();
      airline.setActive(false);
      airlineRepository.save(airline);

    }
    public Airline findAirlineByAirlineName(String airlineName){
        return  getAirlines().stream().filter(airline -> airline.getAirlineName().equals(airlineName)).findFirst().orElse(null);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<Airline> getReferenceById(Long id) {
        return airlineRepository.findById(id);
    }


    public List<AirlineSaveResponse> search(@Valid AirlineSearchRequest searchRequest) {

        Airline airline = Airline.builder()
                .airlineName(searchRequest.getAirlineName())
                .build();
        Example<Airline> airlineExample=Example.of(airline,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        List<Airline> airlines = airlineRepository.findAll(airlineExample);
        return  airlines.stream().map(airline1  ->entityResponse(airline1)).toList();


    }

    public AirlineSaveResponse entityResponse(Airline airline){

        return  AirlineSaveResponse.builder()
                .name(airline.getAirlineName())
                .is_active(airline.getActive())
                .build();

    }
}
