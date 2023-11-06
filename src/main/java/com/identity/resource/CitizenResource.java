package com.identity.resource;

import com.identity.model.request.citizen.CitizenAddRequest;
import com.identity.model.request.citizen.CitizenUpdateRequest;
import com.identity.model.response.citizen.CitizenResponse;
import com.identity.model.response.generic.BaseResponse;
import com.identity.service.CitizenService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/api/citizens")
@AllArgsConstructor
public class CitizenResource {

    private final CitizenService citizenService;

    // CREATE CITIZEN API
    @POST
    @ResponseStatus(201)
    @RolesAllowed({"admin", "citizen"})
    @Operation(description = "Add Citizen Request", summary = "Add Citizen")
    @APIResponses(value = { @APIResponse(responseCode = "201", description = "Citizen Created") })
    public BaseResponse<Object> addCitizen(@Valid CitizenAddRequest request) {
        return BaseResponse.builder()
                .successMsg("Citizen Created")
                .data(citizenService.addCitizen(request))
                .build();
    }

    // UPDATE CITIZEN API
    @PUT
    @Path("/{citizenId}")
    @RolesAllowed({"admin", "citizen"})
    @Operation(description = "Update Citizen Request", summary = "Update Citizen")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Citizen Updated") })
    public BaseResponse<Object> updatedCitizen(
            @PathParam("citizenId") Long citizenId, CitizenUpdateRequest request
    ) {
        return BaseResponse.builder()
                .successMsg("Citizen Updated")
                .data(citizenService.updateCitizen(citizenId, request))
                .build();
    }

    // FETCH CITIZEN API
    @GET
    @Path("/{citizenId}")
    @RolesAllowed({"admin", "citizen"})
    @Operation(description = "Fetch Citizen", summary = "Fetch Citizen")
    @APIResponses(value = { @APIResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CitizenResponse.class))})})
    public BaseResponse<Object> getCitizen(@PathParam("citizenId") Long citizenId) {
        return BaseResponse.builder()
                .successMsg("Citizen Fetched")
                .data(citizenService.getCitizen(citizenId))
                .build();
    }

    // FETCH ALL CITIZENS API
    @GET
    @RolesAllowed({"admin", "citizen"})
    @Operation(description = "Fetch All Citizens", summary = "Fetch All Citizens")
    @APIResponses(value = { @APIResponse(responseCode = "200", content = { @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = CitizenResponse.class))})})
    public BaseResponse<Object> getAllCitizens() {
        return BaseResponse.builder()
                .successMsg("Citizens Fetched")
                .data(citizenService.getAllCitizens())
                .build();
    }

    // DELETE CITIZEN API
    @DELETE
    @Path("/{citizenId}")
    @RolesAllowed("admin")
    @Operation(description = "Delete Citizen", summary = "Delete Citizen")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Citizen Deleted") })
    public BaseResponse<Object> deleteCitizen(@PathParam("citizenId") Long citizenId) {
        return BaseResponse.builder()
                .successMsg("Citizen Deleted")
                .data(citizenService.deleteCitizen(citizenId))
                .build();
    }
}