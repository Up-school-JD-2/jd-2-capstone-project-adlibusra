package io.upschool.dto.CreditCard;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@DataAmount
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardSaveRequest {

    private String nameSurname;
    @Pattern(regexp = "^(\\d{4}\\W?\\d{4}\\W?\\d{4}\\W?\\d{4})$", message = " The credit card number is invalid")
    private String cardNo;
    private String expirationDate;
    private String cvv;
}
