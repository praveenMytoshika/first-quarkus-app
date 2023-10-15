package com.identity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Citizen extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Column(length = 10)
    private Long phone;
    private String country;
    private boolean isActive = true;

}
