package io.upschool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creditCard")
@Builder
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameSurname",unique = true)
    private String nameSurname;


    @Column(name = "cardNo")
    private String cardNo;
    @Pattern(regexp = "^\\d{2}\\/\\d{2}$")
    @Column(name = "expirationDate")
    private String expirationDate;

    @Pattern(regexp = "^[0-9]{3,4}$")
    @Column(name = "cvv" , nullable = false)
    private String cvv;



}
