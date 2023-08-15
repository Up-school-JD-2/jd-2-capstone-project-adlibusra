package io.upschool.dto.Payment;

import io.upschool.entity.CreditCard;
import io.upschool.entity.Flight;
import io.upschool.entity.Passenger;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSaveRequest {

    private Long creditCard;

    private Long passenger;

    private Long flight;
}
