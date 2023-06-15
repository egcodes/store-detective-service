package com.egcodes.storedetectiveservice.exception;

import com.egcodes.storedetectiveservice.exception.errorcode.ErrorCode;
import com.egcodes.storedetectiveservice.service.CorrelationIdHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocatorExceptionTest {

    @Test
    @DisplayName("Test LocatorException constructor")
    public void testLocatorExceptionConstructor() {
        var errorCode = new ErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR");

        CorrelationIdHolder.setCorrelationId("aed3cebe-e009-40f8-8a74-43e7bc05b5a4");

        var exception = new LocatorException(errorCode, new Object[]{});

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getCode());
        assertEquals("INTERNAL_SERVER_ERROR", exception.getMessage());
    }
}
