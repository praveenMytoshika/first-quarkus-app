package com.identity;

import com.identity.entity.Aadhar;
import com.identity.entity.Citizen;
import com.identity.entity.SimCard;
import com.identity.enums.AadharCompany;
import com.identity.enums.Gender;
import com.identity.enums.SimCardProvider;
import com.identity.model.request.aadhar.AadharRequest;
import com.identity.model.request.citizen.CitizenRequest;
import com.identity.model.request.simcard.SimCardRequest;
import com.identity.model.response.aadhar.AadharResponse;
import com.identity.model.response.citizen.CitizenResponse;
import com.identity.model.response.simacard.SimCardResponse;

import java.util.List;

public class TestDataBuilder {

    public static final Long citizenId = 123L;
    public static final Long aadharId = 234L;
    public static final Long simCardId_1 = 345L;
    public static final Long simCardId_2 = 346L;

    public static CitizenRequest buildAddCitizenRequest() {
        return CitizenRequest.builder()
                .firstName("aman")
                .lastName("singh")
                .email("test@gmail.com")
                .phone("9876543211")
                .gender(Gender.M)
                .country("IND")
                .password("test@123")
                .aadhar(buildAadharRequest())
                .simCards(buildSimCardRequestList())
                .build();
    }

    public static AadharRequest buildAadharRequest() {
        return AadharRequest.builder()
                .aadharNumber(22333445566778899L)
                .company(AadharCompany.UIDAI)
                .build();
    }

    public static List<SimCardRequest> buildSimCardRequestList() {
        return List.of(buildSimCardRequest_1(), buildSimCardRequest_2());
    }

    public static SimCardRequest buildSimCardRequest_1() {
        return SimCardRequest.builder()
                .number(1234567899L)
                .provider(SimCardProvider.JIO)
                .build();
    }

    public static SimCardRequest buildSimCardRequest_2() {
        return SimCardRequest.builder()
                .number(1234567898L)
                .provider(SimCardProvider.AIRTEL)
                .build();
    }

    public static Citizen buildCitizen_1() {
        Citizen citizen = Citizen.builder()
                .firstName("aman")
                .lastName("singh")
                .gender(Gender.M)
                .email("test@gmail.com")
                .phone(9876543211L)
                .country("IND")
                .password("test@123")
                .aadhar(buildAadhar_1())
                .simCards(buildSimCardsList_1())
                .build();

        citizen.getAadhar().setCitizen(citizen);
        citizen.getSimCards().forEach(simCard -> simCard.setCitizen(citizen));
        return citizen;
    }

    public static Citizen buildCitizen_2() {
        Citizen citizen = Citizen.builder()
                .firstName("kevin")
                .lastName("lyon")
                .email("klyon@info.com")
                .phone(9876543210L)
                .country("AUS")
                .password("kevin@123")
                .aadhar(buildAadhar_2())
                .simCards(buildSimCardsList_2())
                .build();

        citizen.getAadhar().setCitizen(citizen);
        citizen.getSimCards().forEach(simCard -> simCard.setCitizen(citizen));
        return citizen;
    }

    public static Aadhar buildAadhar_1() {
        return Aadhar.builder()
                .aadharNumber(9988776655443322L)
                .company(AadharCompany.UIDAI)
                .build();
    }

    public static Aadhar buildAadhar_2() {
        return Aadhar.builder()
                .aadharNumber(9988776655443311L)
                .company(AadharCompany.UIDAI)
                .build();
    }

    public static List<SimCard> buildSimCardsList_1() {
        return List.of(buildSimCard_1(), buildSimCard_2());
    }

    public static List<SimCard> buildSimCardsList_2() {
        return List.of(buildSimCard_3(), buildSimCard_4());
    }

    public static SimCard buildSimCard_1() {
        return SimCard.builder()
                .number(1234567899L)
                .provider(SimCardProvider.JIO)
                .build();
    }

    public static SimCard buildSimCard_2() {
        return SimCard.builder()
                .number(1234567898L)
                .provider(SimCardProvider.AIRTEL)
                .build();
    }

    public static SimCard buildSimCard_3() {
        return SimCard.builder()
                .number(1234567897L)
                .provider(SimCardProvider.AIRTEL)
                .build();
    }

    public static SimCard buildSimCard_4() {
        return SimCard.builder()
                .number(1234567896L)
                .provider(SimCardProvider.VODAFONE)
                .build();
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

    private static AadharResponse buildAadharResponse() {
        return new AadharResponse(
                aadharId,
                9988776655443322L,
                AadharCompany.UIDAI,
                true
        );
    }

    private static List<SimCardResponse> buildSimCardResponseList() {
        return List.of(buildSimCardResponse_1(), buildSimCardResponse_2());
    }

    private static SimCardResponse buildSimCardResponse_1() {
        return new SimCardResponse(
                simCardId_1,
                1234567899L,
                SimCardProvider.JIO,
                true
        );
    }
    private static SimCardResponse buildSimCardResponse_2() {
        return new SimCardResponse(
                simCardId_2,
                1234567898L,
                SimCardProvider.AIRTEL,
                true
        );
    }
}
