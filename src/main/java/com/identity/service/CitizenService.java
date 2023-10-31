package com.identity.service;

import com.identity.entity.Citizen;
import com.identity.exception.BadRequestException;
import com.identity.exception.NotFoundException;
import com.identity.mapper.CitizenMapper;
import com.identity.model.request.citizen.CitizenAddRequest;
import com.identity.model.request.citizen.CitizenUpdateRequest;
import com.identity.model.response.citizen.CitizenResponse;
import com.identity.repository.CitizenRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    // ADD/CREATE CITIZEN
    @Transactional
    public boolean addCitizen(CitizenAddRequest request) {
        Optional<Citizen> citizenOpt = citizenRepository.findByEmail(request.getEmail());
        if (citizenOpt.isPresent()) throw new BadRequestException("Citizen email already exist");

        Citizen newCitizen = mapToCitizen(request);

        citizenRepository.persist(newCitizen);
        return citizenRepository.isPersistent(newCitizen);
    }

    // UPDATE CITIZEN
    @Transactional
    public boolean updateCitizen(Long citizenId, CitizenUpdateRequest request) {
        Citizen citizen =
                citizenRepository.findByIdOptional(citizenId)
                        .orElseThrow(() -> new NotFoundException("Citizen not found"));

        Citizen updatedCitizen = citizenMapper.mapToCitizen(request, citizen, citizen.getEmail());

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
        List<Citizen> citizens = citizenRepository.listAll();
        return citizenMapper.mapToCitizenResponse(citizens);
    }


    // DELETE CITIZEN
    @Transactional
    public boolean deleteCitizen(Long citizenId) {
        citizenRepository.findByIdOptional(citizenId)
                        .orElseThrow(() -> new NotFoundException("Citizen not found"));

        return citizenRepository.deleteById(citizenId);
    }

    private Citizen mapToCitizen(CitizenAddRequest request) {
        Citizen newCitizen = citizenMapper.mapToCitizen(request);
        citizenMapper.mapToAadhar(newCitizen, newCitizen.getAadhar());
        newCitizen.getSimCards().forEach(simCard -> citizenMapper.mapToSimCard(newCitizen, simCard));
        return newCitizen;
    }

}
