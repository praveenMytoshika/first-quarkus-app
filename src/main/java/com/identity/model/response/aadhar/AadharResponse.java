package com.identity.model.response.aadhar;

import com.identity.enums.AadharCompany;

public record AadharResponse(Long id, Long aadharNumber, AadharCompany company, boolean isActive) { }
