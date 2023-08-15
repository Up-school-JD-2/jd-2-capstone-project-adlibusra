package io.upschool.service;

import io.upschool.dto.Payment.PaymentSaveRequest;
import io.upschool.dto.Payment.PaymentSaveResponse;
import io.upschool.entity.CreditCard;
import io.upschool.entity.Flight;
import io.upschool.entity.Passenger;
import io.upschool.entity.Payment;
import io.upschool.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
private final PaymentRepository paymentRepository;
private final CreditCardService cardService;
private final  PassengerService passengerService;
private  final FlightService flightService;

public PaymentSaveResponse save(PaymentSaveRequest request){
    Optional<CreditCard> creditCard = cardService.getReferenceById(request.getCreditCard());
    Optional<Passenger>  passenger = passengerService.getReferenceById(request.getPassenger());
    Optional<Flight> flightPrice = flightService.getReferenceById(request.getFlight());

    Payment newPayment=Payment.builder()
            .flight(flightPrice.get())
            .creditCard(creditCard.get())
            .passenger(passenger.get())
            .build();
    Payment savedPayment = paymentRepository.save(newPayment);
  return  PaymentSaveResponse.builder().creditCard(savedPayment.getCreditCard().getCardNo())
            .passenger(savedPayment.getPassenger().getLastName()+ savedPayment.getPassenger().getFirstName())
          .flight(savedPayment.getFlight().getPrice())
            .build();

}
}
