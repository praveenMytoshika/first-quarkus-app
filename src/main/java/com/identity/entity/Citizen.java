package com.identity.entity;

import com.identity.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder.Default
    private boolean isActive = true;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "citizen", orphanRemoval = true)
    private Aadhar aadhar;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizen")
    public List<SimCard> simCards = emptyList();

}
