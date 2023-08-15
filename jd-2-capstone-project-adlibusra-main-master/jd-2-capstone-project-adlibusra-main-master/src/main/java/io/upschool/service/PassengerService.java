package io.upschool.service;

import io.upschool.dto.CreditCard.CardSaveResponse;
import io.upschool.dto.Passenger.PassengerSaveRequest;
import io.upschool.dto.Passenger.PassengerSaveResponse;
import io.upschool.entity.CreditCard;
import io.upschool.entity.Passenger;
import io.upschool.entity.Route;
import io.upschool.exception.PassengerAlreadySavedException;
import io.upschool.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class PassengerService {
    private final PassengerRepository passengerRepository;



    public Passenger savePassenger(PassengerSaveRequest request){

        Boolean aBoolean=passengerRepository.existsByTc(request.getTc());
        if(aBoolean){
            throw new PassengerAlreadySavedException("Bu tc li kişi daha önce eklenmiş");
        }
        Passenger savePassenger = Passenger.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail()).tc(request.getTc())
                .phoneNumber(request.getPhoneNumber()).build();
       return passengerRepository.save(savePassenger);



    }
    public PassengerSaveResponse entityToResponse(Passenger passenger){
        return PassengerSaveResponse.builder()
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber())
                .build();

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<Passenger> getReferenceById(Long id) {
        return passengerRepository.findById(id);
    }
}
