package io.upschool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "passengers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName" )
    @NotEmpty(message = "isim boş bırakılamz")
    private String firstName;

    @NotEmpty(message = "isim boş bırakılamz")
    @Column(name = "lastName")
    private  String lastName;

    @Pattern(regexp = "[0-9\\s]{11}")
    @Column(name = "tc")
    private String tc;

    @Email(message = "Hatalı email girildi")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^\\d{11}$")
    @Column(name = "phoneNumber" , nullable = false, length = 15)
    private String phoneNumber;

}
