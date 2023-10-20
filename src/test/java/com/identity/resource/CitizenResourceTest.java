package com.identity.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.identity.service.CitizenService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static com.identity.TestDataBuilder.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CitizenResourceTest {
    @InjectMock
    CitizenService citizenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAddCitizen_withSuccess() throws JsonProcessingException {
        // Arrange
        Mockito.when(citizenService.addCitizen(buildAddCitizenRequest())).thenReturn(true);

        // Act & Assert
        given()
                .body(objectMapper.writeValueAsString(buildAddCitizenRequest()))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/citizens")
                .then()
                .statusCode(201)
                .body("successMsg", equalTo("Citizen Created"))
                .body("data", is(true));
    }

    @Test
    void testUpdateCitizen_withSuccess() throws JsonProcessingException {
        // Arrange
        Mockito.when(citizenService.updateCitizen(citizenId, buildAddCitizenRequest())).thenReturn(true);

        // Act & Assert
        given()
                .body(objectMapper.writeValueAsString(buildAddCitizenRequest()))
                .contentType(ContentType.JSON)
                .pathParam("citizenId", citizenId)
                .when()
                .put("/api/citizens/{citizenId}")
                .then()
                .statusCode(200)
                .body("successMsg", is("Citizen Updated"))
                .body("data", is(true));
    }

    @Test
    void testGetCitizen_withSuccess() {
        // Arrange
        Mockito.when(citizenService.getCitizen(citizenId)).thenReturn(buildCitizenResponse());

        // Act & Assert
        given()
                .pathParam("citizenId", citizenId)
                .when()
                .get("/api/citizens/{citizenId}")
                .then()
                .statusCode(200)
                .body("successMsg", equalTo("Citizen Fetched"))
                .body("data.firstName", equalTo("test"));
    }

    @Test
    void testGetAllCitizens_withSuccess() {
        // Arrange
        Mockito.when(citizenService.getAllCitizens()).thenReturn(Collections.singletonList(buildCitizenResponse()));

        // Act & Assert
        given()
                .when()
                .get("/api/citizens")
                .then()
                .statusCode(200)
                .body("successMsg", equalTo("Citizens Fetched"))
                .body("data", hasSize(1));
    }

    @Test
    void testDeleteCitizen_withSuccess() {
        // Arrange
        Mockito.when(citizenService.deleteCitizen(citizenId)).thenReturn(true);

        // Act & Assert
        given()
                .pathParam("citizenId", citizenId)
                .when()
                .delete("/api/citizens/{citizenId}")
                .then()
                .statusCode(200)
                .body("successMsg", equalTo("Citizen Deleted"))
                .body("data", is(true));
    }
}
