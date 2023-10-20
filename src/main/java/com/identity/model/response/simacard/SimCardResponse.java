package com.identity.model.response.simacard;

import com.identity.enums.SimCardProvider;

public record SimCardResponse(Long id, Long number, SimCardProvider provider, boolean isActive) { }
