package com.identity;

import com.identity.entity.Aadhar;
import com.identity.enums.AadharCompany;
import com.identity.model.request.aadhar.AadharAddRequest;
import com.identity.model.request.aadhar.AadharUpdateRequest;
import com.identity.model.response.aadhar.AadharResponse;

import java.time.Instant;

public class AadharDataBuilder {

    public static final Long aadharId = 234L;

    public static AadharAddRequest buildAadharAddRequest() {
        return AadharAddRequest.builder()
                .aadharNumber(22333445566778899L)
                .company(AadharCompany.UIDAI)
                .build();
    }

    public static AadharUpdateRequest buildAadharUpdateRequest() {
        return AadharUpdateRequest.builder()
                .id(123L)
                .aadharNumber(9988776655443322L)
                .company(AadharCompany.UIDAI)
                .build();
    }

    protected static AadharResponse buildAadharResponse() {
        return new AadharResponse(
                aadharId,
                9988776655443322L,
                AadharCompany.UIDAI,
                true
        );
    }

    public static Aadhar buildAadhar_1() {
        return Aadhar.builder()
                .id(1L)
                .aadharNumber(9988776655443322L)
                .company(AadharCompany.UIDAI)
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();
    }

    public static Aadhar buildAadhar_2() {
        return Aadhar.builder()
                .id(2L)
                .aadharNumber(9988776655443311L)
                .company(AadharCompany.UIDAI)
                .createdBy("test@gmail.com")
                .createdOn(Instant.now())
                .modifiedOn(Instant.now())
                .build();
    }
}
