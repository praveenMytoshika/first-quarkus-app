package com.identity.entity;

import com.identity.enums.AadharCompany;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aadhar extends AbstractEntity {

    private Long aadharNumber;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AadharCompany company = AadharCompany.UIDAI;

    @Builder.Default
    private boolean isActive = true;

    @OneToOne
    private Citizen citizen;

}
