package io.upschool.service;

import io.upschool.dto.TicketType.TicketTypeSaveRequest;
import io.upschool.dto.TicketType.TickettypeResponse;
import io.upschool.entity.Route;
import io.upschool.entity.TicketType;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.exception.TicketTypeAlreadySavedException;
import io.upschool.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;

    public TickettypeResponse save(TicketTypeSaveRequest request){
         Boolean aBoolean = ticketTypeRepository.existsByticketTypeName(request.getTicketTypeName());
         if(aBoolean){
             throw new TicketTypeAlreadySavedException("Bu veri daha önce  eklenmiş");
         }

        var newTicketType= TicketType.builder().ticketTypeName(request.getTicketTypeName()).build();
        TicketType  savedTicketType = ticketTypeRepository.save(newTicketType);
       return TickettypeResponse.builder().ticketTypeName(savedTicketType.getTicketTypeName()).build();

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<TicketType> getReferenceById(Long id) {
        return ticketTypeRepository.findById(id);
    }
}
