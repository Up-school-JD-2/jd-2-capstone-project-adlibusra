package io.upschool.repository;


import io.upschool.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    Boolean existsByticketTypeName(String ticketTypeName );

    Optional<TicketType> findByTicketTypeName(String iptal);
}
