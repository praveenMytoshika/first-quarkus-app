package com.identity.service;

import com.identity.enums.Roles;
import com.identity.model.response.generic.TokenResponse;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Singleton
@AllArgsConstructor
public class TokenService {

    @ConfigProperty(name = "jwt.issuer")
    public String issuer;

    @ConfigProperty(name = "jwt.subject")
    public String subject;

    public TokenResponse generateToken() {
        Set<String> roles = Set.of(Roles.ADMIN.name(), Roles.CITIZEN.name());
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

        String token = Jwt.issuer("quarkus")
                .subject("citizen")
                .groups(roles)
                .expiresAt(expiresAt.toInstant(ZoneOffset.UTC).toEpochMilli())
                .sign();

        return TokenResponse.builder()
                .token(token)
                .expiresAt(expiresAt)
                .roles(roles)
                .build();
    }
}
