package com.identity.model.response.citizen;

import com.identity.model.response.aadhar.AadharResponse;
import com.identity.model.response.simacard.SimCardResponse;

import java.util.List;

public record CitizenResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String country,
        boolean isActive,
        AadharResponse aadhar,
        List<SimCardResponse> simCards
) { }
