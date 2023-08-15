package io.upschool.service;

import io.upschool.dto.FlightStatus.FlightStatusRequest;
import io.upschool.dto.FlightStatus.FlightStatusResponse;
import io.upschool.entity.FlightStatus;
import io.upschool.entity.Route;
import io.upschool.exception.CityAlreadySavedException;
import io.upschool.exception.StatusAlreadySavedException;
import io.upschool.repository.FlightStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightStatusService {

    private final FlightStatusRepository flightStatusRepository;

    public FlightStatusResponse save(FlightStatusRequest request) {
        int statusCountByName = flightStatusRepository.findStatusCountByName(request.getFlightStatus());
        if (statusCountByName > 0) {
            throw new StatusAlreadySavedException("Bu veri daha önce  eklenmiş");
        }
        var newStatus = FlightStatus.builder()
                .statusName(request.getFlightStatus()).build();
        FlightStatus save = flightStatusRepository.save(newStatus);
        return FlightStatusResponse.builder().flightStatus(save.getStatusName()).build();

    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<FlightStatus> getReferenceById(Long id) {
        return flightStatusRepository.findById(id);
    }
}
