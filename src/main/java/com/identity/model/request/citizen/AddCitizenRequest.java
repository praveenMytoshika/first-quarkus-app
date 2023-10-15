package com.identity.model.request.citizen;

import com.identity.validation.CountryCodeAlpha3;
import com.identity.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCitizenRequest {

    @NotBlank(message = "First name can't be null or blank")
    private String firstName;

    @NotBlank(message = "Last name can't be null or blank")
    private String lastName;

    @NotBlank(message = "Phone number can't be null or blank")
    @Email
    private String email;

    @NotBlank(message = "Password can't be null or blank")
    private String password;

    @PhoneNumber
    private String phone;

    @CountryCodeAlpha3
    private String country;

}
