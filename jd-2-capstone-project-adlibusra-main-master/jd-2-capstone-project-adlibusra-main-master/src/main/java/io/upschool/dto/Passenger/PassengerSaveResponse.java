package io.upschool.dto.Passenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSaveResponse {



    private String firstName;

    private  String lastName;

    private String tc;

    private String email;

    private String phoneNumber;
}
