package io.upschool.repository;

import io.upschool.entity.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandModelRepository extends JpaRepository<BrandModel, Long> {

  BrandModel findByNameIgnoreCase(String name);


}
