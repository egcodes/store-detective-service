package com.egcodes.storedetectiveservice.api.dto;

import com.egcodes.storedetectiveservice.TestContextProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("LocationDTO Test")
public class LocationDTOTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("Should validate location with valid longitude and latitude")
    void testValidateLocationWithValidLongitudeAndLatitude() {
        // Given
        var locationDTO = TestContextProvider.createLocationDTO();

        // When
        var violations = validator.validate(locationDTO);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Should fail validation with invalid longitude")
    void testFailValidationWithInvalidLongitude() {
        // Given
        var locationDTO = TestContextProvider.createLocationDTO();
        locationDTO.setLongitude(200.0);

        // When
        var violations = validator.validate(locationDTO);

        // Then
        assertEquals(1, violations.size());
        var violation = violations.iterator().next();
        assertEquals("Longitude must be less than or equal to 180.0", violation.getMessage());
    }

    @Test
    @DisplayName("Should fail validation with invalid latitude")
    void testFailValidationWithInvalidLatitude() {
        // Given
        var locationDTO = TestContextProvider.createLocationDTO();
        locationDTO.setLatitude(91.0);

        // When
        var violations = validator.validate(locationDTO);

        // Then
        assertEquals(1, violations.size());
        var violation = violations.iterator().next();
        assertEquals("Latitude must be less than or equal to 90.0", violation.getMessage());
    }

    @Test
    @DisplayName("Test longitude getter and setter")
    void testLongitudeGetterAndSetterHaveVarKeyword() {
        // Given & When
        var locationDTO = TestContextProvider.createLocationDTO();
        var longitude = locationDTO.getLongitude();

        // Then
        assertEquals(longitude, locationDTO.getLongitude());
    }

    @Test
    @DisplayName("Test latitude getter and setter")
    void testLatitudeGetterAndSetterHaveVarKeyword() {
        // Given & When
        var locationDTO = TestContextProvider.createLocationDTO();
        var latitude = locationDTO.getLatitude();

        // Then
        assertEquals(latitude, locationDTO.getLatitude());
    }
}
