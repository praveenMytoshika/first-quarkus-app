package com.identity.service;

import com.identity.entity.Citizen;
import com.identity.exception.BadRequestException;
import com.identity.exception.NotFoundException;
import com.identity.model.response.citizen.CitizenResponse;
import com.identity.repository.CitizenRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.identity.TestDataBuilder.*;
import static com.identity.TestDataBuilder.buildAddCitizenRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class CitizenServiceTest {

    @Inject
    CitizenService citizenService;

    @InjectMock
    CitizenRepository citizenRepository;

    @Test
    void testAddCitizen_withSuccess() {
        // Arrange
        Citizen citizen = buildCitizen_1();
        citizen.getAadhar().setCitizen(citizen);
        Mockito.when(citizenRepository.findByEmail(buildAddCitizenRequest().getEmail())).thenReturn(Optional.empty());
        Mockito.doNothing().when(citizenRepository).persist(citizen);
        Mockito.when(citizenRepository.isPersistent(citizen)).thenReturn(true);

        // Act
        boolean result = citizenService.addCitizen(buildAddCitizenRequest());

        // Assert
        assertThat(result).isTrue();
        verify(citizenRepository).persist(citizen);
        verify(citizenRepository).isPersistent(citizen);
    }

    @Test
    void testAddCitizen_withBadRequestException() {
        // Arrange
        Mockito.when(citizenRepository.findByEmail(anyString())).thenReturn(Optional.of(buildCitizen_1()));

        // Act & Assert
        assertThrows(BadRequestException.class, () -> citizenService.addCitizen(buildAddCitizenRequest()));
    }

    @Test
    void testUpdateCitizen_withSuccess() {
        // Arrange
        Citizen citizen = buildCitizen_1();
        Mockito.when(citizenRepository.findByIdOptional(citizenId)).thenReturn(Optional.of(citizen));
        Mockito.doNothing().when(citizenRepository).persist(citizen);
        Mockito.when(citizenRepository.isPersistent(citizen)).thenReturn(true);

        // Act
        boolean result = citizenService.updateCitizen(citizenId, buildAddCitizenRequest());

        // Assert
        assertThat(result).isTrue();
        verify(citizenRepository).findByIdOptional(citizenId);
        verify(citizenRepository).persist(citizen);
        verify(citizenRepository).isPersistent(citizen);
    }

    @Test
    void testUpdateCitizen_withNotFoundException() {
        // Arrange
        Mockito.when(citizenRepository.findByIdOptional(citizenId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> citizenService.updateCitizen(citizenId, buildAddCitizenRequest()));
    }

    @Test
    void testGetCitizen_withSuccess() {
        // Arrange
        Mockito.when(citizenRepository.findByIdOptional(citizenId)).thenReturn(Optional.of(buildCitizen_1()));

        // Act
        CitizenResponse result = citizenService.getCitizen(citizenId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.firstName()).isEqualTo("aman");
        assertThat(result.country()).isEqualTo("IND");
        verify(citizenRepository).findByIdOptional(citizenId);
    }

    @Test
    void testGetCitizen_withNotFoundException() {
        // Arrange
        Mockito.when(citizenRepository.findByIdOptional(citizenId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> citizenService.getCitizen(citizenId));
    }

    @Test
    void testGetAllCitizens_withSuccess() {
        // Arrange
        Mockito.when(citizenRepository.listAll()).thenReturn(List.of(buildCitizen_1(), buildCitizen_2()));

        // Act
        List<CitizenResponse> result = citizenService.getAllCitizens();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).country()).isEqualTo("IND");
        assertThat(result.get(1).country()).isEqualTo("AUS");
        verify(citizenRepository).listAll();
    }

    @Test
    void testDeleteCitizen_withSuccess() {
        // Arrange
        Mockito.when(citizenRepository.findByIdOptional(citizenId)).thenReturn(Optional.of(buildCitizen_1()));
        Mockito.when(citizenRepository.deleteById(citizenId)).thenReturn(true);

        // Act
        boolean result = citizenService.deleteCitizen(citizenId);

        // Assert
        assertThat(result).isTrue();
        verify(citizenRepository).findByIdOptional(citizenId);
        verify(citizenRepository).deleteById(citizenId);
    }

    @Test
    void testDeleteCitizen_withNotFoundException() {
        // Arrange
        Mockito.when(citizenRepository.findByIdOptional(citizenId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> citizenService.deleteCitizen(citizenId));
    }
}
