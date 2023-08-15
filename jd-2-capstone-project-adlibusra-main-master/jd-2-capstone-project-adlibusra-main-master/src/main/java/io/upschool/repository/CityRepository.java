package io.upschool.repository;

import io.upschool.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select count(a) from City a " +
            "where a.cityName = :cityName")
    int findCityCountByName(@Param("cityName") String cityName);
}
