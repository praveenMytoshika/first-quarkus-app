package com.identity.mapper;

import com.identity.entity.Citizen;
import com.identity.model.request.citizen.AddCitizenRequest;
import com.identity.model.response.citizen.CitizenResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CitizenMapper {

    Citizen mapToCitizen(AddCitizenRequest request);

    Citizen mapToCitizen(AddCitizenRequest request, @MappingTarget Citizen citizen);

    CitizenResponse mapToCitizenResponse(Citizen citizen);

    List<CitizenResponse> mapToCitizenResponse(List<Citizen> citizens);
}
