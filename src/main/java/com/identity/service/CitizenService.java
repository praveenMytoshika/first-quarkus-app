package com.identity.service;

import com.identity.entity.Citizen;
import com.identity.exception.NotFoundException;
import com.identity.mapper.CitizenMapper;
import com.identity.model.request.citizen.AddCitizenRequest;
import com.identity.model.response.citizen.CitizenResponse;
import com.identity.repository.CitizenRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    // ADD/CREATE CITIZEN
    @Transactional
    public boolean addCitizen(AddCitizenRequest request) {
        Citizen citizen = citizenMapper.mapToCitizen(request);
        citizen.setCreatedBy(request.getEmail());
        citizen.setCreatedOn(Instant.now());
        citizenRepository.persist(citizen);
        return citizenRepository.isPersistent(citizen);
    }

    // UPDATE CITIZEN
    @Transactional
    public boolean updateCitizen(Long citizenId, AddCitizenRequest request) {
        Citizen citizen =
                citizenRepository.findByIdOptional(citizenId)
                        .orElseThrow(() -> new NotFoundException("Citizen not found"));

        Citizen updatedCitizen = citizenMapper.mapToCitizen(request, citizen);
        updatedCitizen.setModifiedBy(request.getEmail());
        updatedCitizen.setModifiedOn(Instant.now());
        citizenRepository.persist(updatedCitizen);
        return citizenRepository.isPersistent(updatedCitizen);
    }

    // FETCH CITIZEN
    public CitizenResponse getCitizen(Long citizenId) {
        Citizen citizen =
                citizenRepository.findByIdOptional(citizenId)
                        .orElseThrow(() -> new NotFoundException("Citizen not found"));

        return citizenMapper.mapToCitizenResponse(citizen);
    }

    // FETCH ALL CITIZENS
    public List<CitizenResponse> getAllCitizens() {
        List<Citizen> citizens = citizenRepository.findAll().stream().toList();
        return citizenMapper.mapToCitizenResponse(citizens);
    }


    // DELETE CITIZEN
    @Transactional
    public boolean deleteCitizen(Long citizenId) {
        citizenRepository.findByIdOptional(citizenId)
                        .orElseThrow(() -> new NotFoundException("Citizen not found"));

        return citizenRepository.deleteById(citizenId);
    }
}
