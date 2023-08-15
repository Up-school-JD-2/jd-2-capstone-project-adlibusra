package io.upschool.service;

import io.upschool.dto.CreditCard.CardSaveRequest;
import io.upschool.dto.CreditCard.CardSaveResponse;
import io.upschool.dto.Passenger.PassengerSaveRequest;
import io.upschool.dto.Passenger.PassengerSaveResponse;
import io.upschool.dto.Ticket.TicketSaveRequest;
import io.upschool.dto.Ticket.TicketSaveResponse;
import io.upschool.dto.Ticket.TicketSearchRequest;
import io.upschool.dto.airportDto.AirportSaveResponse;
import io.upschool.dto.airportDto.AirportSearchRequest;
import io.upschool.dto.cityDto.CitySaveResponse;
import io.upschool.entity.*;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.exception.TicketAlreadySavedException;
import io.upschool.exception.TicketCapacityException;
import io.upschool.repository.TicketRepository;
import io.upschool.repository.TicketTypeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Data
public class TicketService {
private  final TicketRepository ticketRepository;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final TicketTypeService typeService;
    private final CreditCardService cardService;
    private final TicketTypeRepository ticketTypeRepository;

    public TicketSaveResponse save(TicketSaveRequest request){
        Boolean aBoolean = ticketRepository.existsByTicketNumber(request.getTicketNumber());

        if(aBoolean){
            throw new TicketAlreadySavedException("Bu veri daha önce  eklenmiş");
        }
        Optional<Flight> flightId = flightService.getReferenceById(request.getFlightId());
        CardSaveRequest cardSaveRequest = request.getCardSaveRequest();
        CreditCard saveCard = cardService.save(cardSaveRequest);
        PassengerSaveRequest passengerSaveRequest = request.getPassengerSaveRequest();
        Passenger savedPassenger = passengerService.savePassenger(passengerSaveRequest);
        Optional<TicketType> ticketType = typeService.getReferenceById(request.getTicketTypeName());
        CardSaveResponse cardSaveResponse = cardService.entityToResponse(saveCard);
        PassengerSaveResponse passengerSaveResponse =passengerService.entityToResponse(savedPassenger);
        Ticket savedTicket = ticketRepository.save(Ticket.builder()
                .ticketNumber(request.getTicketNumber() )
                .ticketTypeName(ticketType.get())
                .creditCard(saveCard)
                .passenger(savedPassenger)
                .flight(flightId.get())
                .ticketIssuedMoment(request.getTicketIssuedMoment())
                .build());


        return  TicketSaveResponse.builder()
                .ticketNumber(savedTicket.getTicketNumber())
                .ticketTypeName(savedTicket.getTicketTypeName().getTicketTypeName())
                .flightPrice(savedTicket.getFlight().getPrice())
                .cardSaveResponse(cardSaveResponse)
                .passengerSaveResponse(passengerSaveResponse)
                //.passenger(ticketSaveResponse.getPassenger().getEmail() + ticketSaveResponse.getPassenger().getLastName())
                .ticketIssuedMoment(savedTicket.getTicketIssuedMoment())
                .build();
    }

    public List<TicketSaveResponse> searchTicketNumber(TicketSearchRequest searchRequest){
        Ticket ticket=Ticket.builder()
                .ticketNumber(searchRequest.getTicketNumber())
                .build();
        Example<Ticket> ticketExample = Example.of(ticket,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        List<Ticket> ticketList = ticketRepository.findAll(ticketExample);
        return  ticketList.stream().map(airport1-> entityResponse(airport1)).toList();

    }
    public TicketSaveResponse entityResponse(Ticket ticket){

        return  TicketSaveResponse.builder()
                .ticketNumber(ticket.getTicketNumber())
                .ticketIssuedMoment(ticket.getTicketIssuedMoment()).build();

    }
    public void delete(Long id){
        var ticket = ticketRepository.findById(id).get();
        Optional<TicketType> iptal = ticketTypeRepository.findByTicketTypeName("iptal");
        ticket.setTicketTypeName(iptal.get());
        ticketRepository.save(ticket);

    }
}
