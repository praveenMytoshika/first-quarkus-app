package com.identity.model.request.aadhar;

import com.identity.enums.AadharCompany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AadharAddRequest {
    private Long aadharNumber;

    @Builder.Default
    private AadharCompany company = AadharCompany.UIDAI;
}
