package com.identity.mapper;

import com.identity.entity.Aadhar;
import com.identity.entity.Citizen;
import com.identity.entity.SimCard;
import com.identity.model.request.citizen.CitizenRequest;
import com.identity.model.response.citizen.CitizenResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CitizenMapper {

    @Mapping(target = "createdBy", source = "request.email")
    Citizen mapToCitizen(CitizenRequest request);

    @Mapping(target = "aadhar.citizen", source = "citizen")
    @Mapping(target = "aadhar.id", ignore = true)
    @Mapping(target = "aadhar.createdBy", source = "citizen.email")
    @Mapping(target = "aadhar.createdOn", ignore = true)
    @Mapping(target = "aadhar.modifiedOn", ignore = true)
    @Mapping(target = "aadhar.active", ignore = true)
    Aadhar mapToAadhar(Citizen citizen, @MappingTarget Aadhar aadhar);

    @Mapping(target = "simCard.citizen", source = "citizen")
    @Mapping(target = "simCard.id", ignore = true)
    @Mapping(target = "simCard.createdBy", source = "citizen.email")
    @Mapping(target = "simCard.createdOn", ignore = true)
    @Mapping(target = "simCard.modifiedOn", ignore = true)
    @Mapping(target = "simCard.active", ignore = true)
    SimCard mapToSimCard(Citizen citizen, @MappingTarget SimCard simCard);

    @Mapping(target = "citizen.modifiedBy", source = "email")
    @Mapping(target = "citizen.simCards", source = "request.simCards")
    @Mapping(target = "citizen.aadhar", source = "request.aadhar")
    Citizen mapToCitizen(CitizenRequest request, @MappingTarget Citizen citizen, String email);

    CitizenResponse mapToCitizenResponse(Citizen citizen);

    List<CitizenResponse> mapToCitizenResponse(List<Citizen> citizens);
}
