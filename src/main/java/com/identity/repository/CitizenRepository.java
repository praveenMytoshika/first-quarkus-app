package com.identity.repository;

import com.identity.entity.Citizen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CitizenRepository implements PanacheRepository<Citizen> {
}
