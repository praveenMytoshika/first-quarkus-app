package com.identity;

import com.identity.entity.SimCard;
import com.identity.enums.SimCardProvider;
import com.identity.model.request.simcard.SimCardAddRequest;
import com.identity.model.request.simcard.SimCardUpdateRequest;
import com.identity.model.response.simacard.SimCardResponse;

import java.time.Instant;
import java.util.List;

public class SimCardDataBuilder {

    public static final Long simCardId_1 = 345L;
    public static final Long simCardId_2 = 346L;

    public static List<SimCardAddRequest> buildSimCardsAddRequest() {
        return List.of(buildSimCardAddRequest_1(), buildSimCardAddRequest_2());
    }

    public static List<SimCardUpdateRequest> buildSimCardsUpdateRequest() {
        return List.of(buildSimCardUpdateRequest_1(), buildSimCardUpdateRequest_2());
    }

    public static List<SimCard> buildSimCardsList_1() {
        return List.of(buildSimCard_1(), buildSimCard_2());
    }

    public static List<SimCard> buildSimCardsList_2() {
        return List.of(buildSimCard_3(), buildSimCard_4());
    }

    public static List<SimCardResponse> buildSimCardResponseList() {
        return List.of(buildSimCardResponse_1(), buildSimCardResponse_2());
    }

    public static SimCardAddRequest buildSimCardAddRequest_1() {
        return SimCardAddRequest.builder()
                .number(1234567899L)
                .provider(SimCardProvider.JIO)
                .build();
    }

    public static SimCardAddRequest buildSimCardAddRequest_2() {
        return SimCardAddRequest.builder()
                .number(1234567898L)
                .provider(SimCardProvider.AIRTEL)
                .build();
    }

    public static SimCardUpdateRequest buildSimCardUpdateRequest_1() {
        return SimCardUpdateRequest.builder()
                .id(1L)
                .number(9987654321L)
                .provider(SimCardProvider.AIRTEL)
                .build();
    }

    public static SimCardUpdateRequest buildSimCardUpdateRequest_2() {
        return SimCardUpdateRequest.builder()
                .id(2L)
                .number(8987654321L)
                .provider(SimCardProvider.JIO)
                .build();
    }

    public static SimCard buildSimCard_1() {
        return SimCard.builder()
                .id(1L)
                .number(1234567899L)
                .provider(SimCardProvider.JIO)
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();
    }

    public static SimCard buildSimCard_2() {
        return SimCard.builder()
                .id(2L)
                .number(1234567898L)
                .provider(SimCardProvider.AIRTEL)
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();
    }

    public static SimCard buildSimCard_3() {
        return SimCard.builder()
                .id(3L)
                .number(1234567897L)
                .provider(SimCardProvider.AIRTEL)
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();
    }

    public static SimCard buildSimCard_4() {
        return SimCard.builder()
                .id(4L)
                .number(1234567896L)
                .provider(SimCardProvider.VODAFONE)
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();
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
