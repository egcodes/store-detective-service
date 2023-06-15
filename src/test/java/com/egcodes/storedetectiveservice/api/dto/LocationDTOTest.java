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
