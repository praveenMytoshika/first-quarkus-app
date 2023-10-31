package com.identity.mapper;

import com.identity.entity.Aadhar;
import com.identity.entity.Citizen;
import com.identity.entity.SimCard;
import com.identity.model.request.citizen.CitizenAddRequest;
import com.identity.model.request.citizen.CitizenUpdateRequest;
import com.identity.model.request.simcard.SimCardUpdateRequest;
import com.identity.model.response.citizen.CitizenResponse;
import com.identity.model.response.simacard.SimCardResponse;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CitizenMapper {

    @Mapping(target = "createdBy", source = "request.email")
    Citizen mapToCitizen(CitizenAddRequest request);

    @Mapping(target = "aadhar.citizen", source = "citizen")
    @Mapping(target = "aadhar.id", ignore = true)
    @Mapping(target = "aadhar.createdBy", source = "citizen.email")
    @Mapping(target = "aadhar.createdOn", ignore = true)
    @Mapping(target = "aadhar.modifiedOn", ignore = true)
    @Mapping(target = "aadhar.active", ignore = true)
    void mapToAadhar(Citizen citizen, @MappingTarget Aadhar aadhar);

    @Mapping(target = "simCard.citizen", source = "citizen")
    @Mapping(target = "simCard.id", ignore = true)
    @Mapping(target = "simCard.createdBy", source = "citizen.email")
    @Mapping(target = "simCard.createdOn", ignore = true)
    @Mapping(target = "simCard.modifiedOn", ignore = true)
    @Mapping(target = "simCard.active", ignore = true)
    void mapToSimCard(Citizen citizen, @MappingTarget SimCard simCard);

    @Mapping(target = "citizen.modifiedBy", source = "email")
    @Mapping(target = "citizen.simCards", expression = "java(mapToSimCards(request.simCards, citizen.simCards, email))")
    @Mapping(target = "citizen.aadhar.aadharNumber", source = "request.aadhar.aadharNumber")
    @Mapping(target = "citizen.aadhar.company", source = "request.aadhar.company")
    @Mapping(target = "citizen.aadhar.modifiedBy", source = "email")
    Citizen mapToCitizen(CitizenUpdateRequest request, @MappingTarget Citizen citizen, String email);

    default List<SimCard> mapToSimCards(List<SimCardUpdateRequest> request, List<SimCard> target, String email) {
        if ( request == null ) return target;

        List<SimCard> updatedSimCards = new ArrayList<>(target);
        for ( SimCardUpdateRequest simCardRequest : request ) {
            SimCard simCard = findMatchingSimCard(target, simCardRequest.getId());
            updatedSimCards.add(simCardRequestToSimCard(simCardRequest, simCard, email));
        }

        return updatedSimCards;
    }

    default SimCard findMatchingSimCard(List<SimCard> target, Long id) {
        return target.stream().filter(s -> Objects.equals(s.getId(), id)).findFirst().orElse(null);
    }

    @Mapping(target = "target.modifiedBy", source = "email")
    SimCard simCardRequestToSimCard(SimCardUpdateRequest request, @MappingTarget SimCard target, String email);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "aadhar.isActive", source = "aadhar.active")
    CitizenResponse mapToCitizenResponse(Citizen citizen);

    @Mapping(target = "isActive", source = "active")
    SimCardResponse mapToSimCardResponse(SimCard simCard);

    List<CitizenResponse> mapToCitizenResponse(List<Citizen> citizens);
}
