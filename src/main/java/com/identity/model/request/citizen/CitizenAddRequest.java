package com.identity.model.request.citizen;

import com.identity.enums.Gender;
import com.identity.model.request.aadhar.AadharAddRequest;
import com.identity.model.request.simcard.SimCardAddRequest;
import com.identity.validation.CountryCodeAlpha3;
import com.identity.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitizenAddRequest {

    @NotBlank(message = "First name can't be null or blank")
    private String firstName;

    @NotBlank(message = "Last name can't be null or blank")
    private String lastName;

    @NotBlank(message = "Email number can't be null or blank")
    @Email
    private String email;

    @NotBlank(message = "Password can't be null or blank")
    private String password;

    @PhoneNumber
    private String phone;

    @CountryCodeAlpha3
    private String country;

    private Gender gender;
    private AadharAddRequest aadhar;
    public List<SimCardAddRequest> simCards;
}
