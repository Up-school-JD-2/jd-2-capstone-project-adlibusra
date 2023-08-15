package io.upschool.repository;

import io.upschool.entity.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightStatusRepository extends JpaRepository<FlightStatus, Long> {
    @Query(value = "select count(f) from FlightStatus f " +
            "where f.statusName = :statusName")
    int findStatusCountByName(@Param("statusName") String statusName);
}
