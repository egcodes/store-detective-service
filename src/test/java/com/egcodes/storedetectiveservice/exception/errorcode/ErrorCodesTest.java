package com.egcodes.storedetectiveservice.exception.errorcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorCodesTest {

    @Test
    @DisplayName("Test ErrorCodes")
    public void testErrorCodes() {
        var generalLocatorException = ErrorCodes.GENERAL_LOCATOR_EXCEPTION;
        assertEquals(1001, generalLocatorException.getCode());
        assertEquals("GENERAL_LOCATOR_EXCEPTION", generalLocatorException.getMessage());

        var locationCantBeEmpty = ErrorCodes.LOCATION_CANT_BE_EMPTY;
        assertEquals(1002, locationCantBeEmpty.getCode());
        assertEquals("LOCATION_CANT_BE_EMPTY", locationCantBeEmpty.getMessage());
    }
}
