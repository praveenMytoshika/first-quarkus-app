package com.identity.resource;

import com.identity.model.response.generic.BaseResponse;
import com.identity.service.TokenService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/api/token")
@AllArgsConstructor
public class TokenResource {

    private final TokenService tokenService;

    // GENERATE TOKEN API
    @GET
    @Operation(description = "Generate Auth Token", summary = "Generate Auth Token")
    @APIResponses(value = { @APIResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class))})})
    public BaseResponse<Object> generateToken() {
        return BaseResponse.builder()
                .successMsg("Token Generated")
                .data(tokenService.generateToken())
                .build();
    }
}
