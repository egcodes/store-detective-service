package com.egcodes.storedetectiveservice.api.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ErrorResponse Tests")
public class ErrorResponseTest {

    @Test
    @DisplayName("Test ErrorResponse with mock data")
    void testCreateErrorResponseWithBuilder() {
        // Given
        var corrId = "aed3cebe-e009-40f8-8a74-43e7bc05b5a4";
        var code = 500;
        var type = "Internal Server Error";
        var message = "Something went wrong";

        // When
        var errorResponse = ErrorResponse.builder()
            .corrId(corrId)
            .code(code)
            .type(type)
            .message(message)
            .build();

        // Then
        assertEquals(corrId, errorResponse.getCorrId());
        assertEquals(code, errorResponse.getCode());
        assertEquals(type, errorResponse.getType());
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    @DisplayName("Test ErrorResponse with default values")
    void testCreateErrorResponseWithDefaultValues() {
        //When
        var errorResponse = ErrorResponse.builder().build();

        // Then
        assertNull(errorResponse.getCorrId());
        assertEquals(0, errorResponse.getCode());
        assertNull(errorResponse.getType());
        assertNull(errorResponse.getMessage());
    }
}
