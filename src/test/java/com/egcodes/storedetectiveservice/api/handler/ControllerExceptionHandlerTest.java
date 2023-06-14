package com.egcodes.storedetectiveservice.api.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.egcodes.storedetectiveservice.exception.LocatorException;
import com.egcodes.storedetectiveservice.exception.errorcode.ErrorCodes;
import com.egcodes.storedetectiveservice.service.message.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@DisplayName("Controller Exception Handler Unit Tests")
@ExtendWith(MockitoExtension.class)
public class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    @Mock
    private MessageService messageService;

    @Test
    @DisplayName("Should handle LocatorException")
    public void handleLocatorException() {
        // Given
        var ex = new LocatorException(ErrorCodes.GENERAL_LOCATOR_EXCEPTION, new Object[]{"param"});

        // When
        var response = exceptionHandler.handleLocatorException(ex);

        // Then
        assertEquals(ErrorCodes.GENERAL_LOCATOR_EXCEPTION.getCode(), response.getCode());
        assertEquals(ErrorCodes.GENERAL_LOCATOR_EXCEPTION.getMessage(), response.getType());
        assertEquals(messageService.get(ErrorCodes.GENERAL_LOCATOR_EXCEPTION.getMessage(), new Object[]{"param"}), response.getMessage());
    }

    @Test
    @DisplayName("Should handle RuntimeException")
    public void handleException() {
        // Given
        var ex = new Exception("Test exception");

        // When
        var response = exceptionHandler.handleException(ex);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), response.getType());
        assertEquals(ex.getMessage(), response.getMessage());
    }

    @Test
    @DisplayName("Should handle MethodArgumentTypeMismatchException")
    public void handleMethodArgumentTypeMismatchException() {
        // Given
        var ex = new MethodArgumentTypeMismatchException(null, null, null, null, null);

        // When
        var response = exceptionHandler.handleBadRequestException(ex);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getCode());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getType());
        assertEquals(ex.getMessage(), response.getMessage());
    }
}
