package io.upschool.repository;

import io.upschool.entity.Route;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query(value = "select count(r) from Route r " +
            "where r.routeName = :routeName")

    int findRouteCountName(@Param("routeName") String routName);
}
