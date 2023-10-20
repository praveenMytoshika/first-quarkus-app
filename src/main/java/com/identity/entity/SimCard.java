package com.identity.entity;

import com.identity.enums.SimCardProvider;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SimCard extends AbstractEntity {

    private Long number;

    @Enumerated(EnumType.STRING)
    private SimCardProvider provider;

    @Builder.Default
    private boolean isActive = true;

    @ManyToOne
    private Citizen citizen;

}
