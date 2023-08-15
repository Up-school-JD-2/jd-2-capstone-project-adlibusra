package io.upschool.repository;

import io.upschool.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    //public List<Plane> findByNameIgnoreCase(String planeName);

    List<Plane> findByNameContainingIgnoreCase(String name);
}
