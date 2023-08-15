package io.upschool.dto.CreditCard;

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
public class CardSaveResponse {
    private String nameSurname;
    private String cardNo;
    private String expirationDate;
    private String cvv;
}
