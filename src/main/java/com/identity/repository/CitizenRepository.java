package com.identity.repository;

import com.identity.entity.Citizen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CitizenRepository implements PanacheRepository<Citizen> {

    public Optional<Citizen> findByEmail(String email) {
        return find("email", email).stream().findFirst();
    }

}
