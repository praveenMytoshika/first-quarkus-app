package com.identity.model.request.citizen;

import com.identity.enums.Gender;
import com.identity.model.request.aadhar.AadharUpdateRequest;
import com.identity.model.request.simcard.SimCardUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitizenUpdateRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String country;
    private Gender gender;
    private AadharUpdateRequest aadhar;
    public List<SimCardUpdateRequest> simCards;
}
