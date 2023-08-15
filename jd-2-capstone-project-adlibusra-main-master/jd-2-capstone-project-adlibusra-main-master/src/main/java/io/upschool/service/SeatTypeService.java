package io.upschool.service;

import io.upschool.dto.Seat.SeatTypeResponse;
import io.upschool.dto.Seat.SeatTypeSaveRequest;
import io.upschool.entity.PlaneBrand;
import io.upschool.entity.SeatType;
import io.upschool.repository.SeatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatTypeService {
    private final SeatTypeRepository seatTypeRepository;

    public SeatTypeResponse save( SeatTypeSaveRequest request){
        var newSeatType = SeatType.builder().name(request.getName()).build();
        SeatType seatType=seatTypeRepository.save(newSeatType);

        return  SeatTypeResponse.builder().name(seatType.getName()).build();

    }
    @Transactional(readOnly = true)
    public Optional<SeatType> getReferenceById(Long id) {
        return seatTypeRepository.findById(id);
    }
}
