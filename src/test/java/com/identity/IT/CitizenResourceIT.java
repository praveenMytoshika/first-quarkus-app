//package com.identity.IT;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.identity.entity.Citizen;
//import com.identity.repository.CitizenRepository;
//import io.quarkus.test.TestTransaction;
//import io.quarkus.test.common.QuarkusTestResource;
//import io.quarkus.test.common.http.TestHTTPResource;
//import io.quarkus.test.h2.H2DatabaseTestResource;
//import io.quarkus.test.junit.QuarkusTest;
//import io.restassured.http.ContentType;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Test;
//
//import java.net.URI;
//
//import static com.identity.TestDataBuilder.buildAddCitizenRequest;
//import static com.identity.TestDataBuilder.buildCitizen_1;
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.is;
//
//@QuarkusTest
//public class CitizenResourceIT {
//
//    @TestHTTPResource
//    private URI uri;
//
//    @Inject
//    CitizenRepository citizenRepository;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void testAddCitizen_withSuccess() throws JsonProcessingException {
//
//        given()
//                .body(objectMapper.writeValueAsString(buildAddCitizenRequest()))
//                .contentType(ContentType.JSON)
//                .when()
//                .post("/api/citizens")
//                .then()
//                .statusCode(201)
//                .body("successMsg", equalTo("Citizen Created"))
//                .body("data", is(true));
//    }
//
//    private Citizen addCitizenInDB() {
//        Citizen citizen = buildCitizen_1();
//        citizenRepository.persist(citizen);
//        return citizen;
//    }
//
//
//
//
//}
