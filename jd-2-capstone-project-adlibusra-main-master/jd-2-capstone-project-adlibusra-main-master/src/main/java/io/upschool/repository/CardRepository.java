package io.upschool.repository;

import io.upschool.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CreditCard, Long> {
    Boolean existsByCardNo(String cardNo);
}
