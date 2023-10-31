package com.identity;

import com.identity.entity.Citizen;
import com.identity.enums.Gender;
import com.identity.model.request.citizen.CitizenAddRequest;
import com.identity.model.request.citizen.CitizenUpdateRequest;
import com.identity.model.response.citizen.CitizenResponse;

import java.time.Instant;

import static com.identity.AadharDataBuilder.*;
import static com.identity.SimCardDataBuilder.*;

public class CitizenDataBuilder {

    public static final Long citizenId = 123L;

    public static CitizenAddRequest buildAddCitizenRequest() {
        return CitizenAddRequest.builder()
                .firstName("aman")
                .lastName("singh")
                .email("test@gmail.com")
                .phone("9876543211")
                .gender(Gender.M)
                .country("IND")
                .password("test@123")
                .aadhar(buildAadharAddRequest())
                .simCards(buildSimCardsAddRequest())
                .build();
    }

    public static CitizenUpdateRequest buildUpdateCitizenRequest() {
        return CitizenUpdateRequest.builder()
                .firstName("aman")
                .lastName("singh")
                .phone("9876543211")
                .gender(Gender.M)
                .country("IND")
                .password("test@123")
                .aadhar(buildAadharUpdateRequest())
                .simCards(buildSimCardsUpdateRequest())
                .build();
    }

    public static Citizen buildCitizen_1() {
        Citizen citizen = Citizen.builder()
                .id(1L)
                .firstName("aman")
                .lastName("singh")
                .gender(Gender.M)
                .email("test@gmail.com")
                .phone(9876543211L)
                .country("IND")
                .password("test@123")
                .aadhar(buildAadhar_1())
                .simCards(buildSimCardsList_1())
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();

        citizen.getAadhar().setCitizen(citizen);
        citizen.getSimCards().forEach(simCard -> simCard.setCitizen(citizen));
        return citizen;
    }

    public static Citizen buildCitizen_2() {
        Citizen citizen = Citizen.builder()
                .id(2L)
                .firstName("kevin")
                .lastName("lyon")
                .email("klyon@info.com")
                .phone(9876543210L)
                .country("AUS")
                .password("kevin@123")
                .aadhar(buildAadhar_2())
                .simCards(buildSimCardsList_2())
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();

        citizen.getAadhar().setCitizen(citizen);
        citizen.getSimCards().forEach(simCard -> simCard.setCitizen(citizen));
        return citizen;
    }

    public static CitizenResponse buildCitizenResponse() {
        return new CitizenResponse(
                citizenId,
                "test",
                "",
                "test@gmail.com",
                "9876543211",
                "IND",
                true,
                buildAadharResponse(),
                buildSimCardResponseList()
        );
    }
}
