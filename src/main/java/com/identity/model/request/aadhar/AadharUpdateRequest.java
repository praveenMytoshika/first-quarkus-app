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
public class AadharUpdateRequest {
    private Long id;
    private Long aadharNumber;
    private AadharCompany company;
}
