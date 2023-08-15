package io.upschool.service;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.CreditCard.CardSaveRequest;
import io.upschool.dto.CreditCard.CardSaveResponse;
import io.upschool.dto.Flight.FlightSaveResponse;
import io.upschool.entity.CreditCard;
import io.upschool.entity.Route;
import io.upschool.exception.CardAlreadySavedException;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.repository.CardRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
@Transactional
public class CreditCardService {
    private  final CardRepository cardRepository;

    public CreditCard save(CardSaveRequest request){
        Boolean aBoolean=cardRepository.existsByCardNo(request.getCardNo());
        if(aBoolean){
            throw new CardAlreadySavedException("Bu kart daha önce eklenmiş");
        }

        String s = maskCreditCardNumber(request.getCardNo());


        CreditCard newCreditCard = CreditCard.builder()
                .cardNo(s)
                .expirationDate(request.getExpirationDate())
                .nameSurname(request.getNameSurname())
                .cvv(request.getCvv())
                .build();
     return cardRepository.save(newCreditCard);

    }
    public CardSaveResponse entityToResponse(CreditCard creditCard){
        return CardSaveResponse.builder()
                .cardNo(creditCard.getCardNo())
                .expirationDate(creditCard.getExpirationDate())
                .nameSurname(creditCard.getNameSurname())
                .cvv(creditCard.getCvv()).build();
    }

    public String maskCreditCardNumber(String creditCard){
        String digitsOnly=creditCard.replaceAll("\\D", "");
        String maskedDigits = "*".repeat(digitsOnly.length()- 10);
        return  digitsOnly.substring(0,6) + maskedDigits+ digitsOnly.substring(digitsOnly.length()-4);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<CreditCard> getReferenceById(Long id) {
        return cardRepository.findById(id);
    }
}
