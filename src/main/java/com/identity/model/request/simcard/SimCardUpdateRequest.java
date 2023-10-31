package com.identity.model.request.simcard;

import com.identity.enums.SimCardProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimCardUpdateRequest {
    private Long id;
    private Long number;
    private SimCardProvider provider;
}
