package io.upschool.repository;

import io.upschool.entity.Airport;
import io.upschool.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query(value = "select a from City a where a.id = :id")
    List<City> findAllByCityIs2(@Param("id") Long sayi);

    Boolean existsByAirportName(String airportName);


    //  @Query(value = "Select a from airporst a where a.id= :id")
   // Airport findAirportById(@Param("id") Long id);
}
