package com.egcodes.storedetectiveservice.service.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

@DisplayName("Message Service Tests")
@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @InjectMocks
    private MessageServiceImpl messageService;

    @Mock
    private MessageSource messageSource;

    @Test
    @DisplayName("Test get with message")
    void testGetWithMessage() {
        // Given
        var message = "test.message";
        var expectedResult = "Test Message";

        // When
        when(messageSource.getMessage(eq(message), isNull(), any(Locale.class))).thenReturn(expectedResult);
        var result = messageService.get(message);

        // Then
        assertEquals(expectedResult, result);
        verify(messageSource).getMessage(eq(message), isNull(), any(Locale.class));
    }

    @Test
    @DisplayName("Test get with message and arguments")
    void testGetWithMessageAndArguments() {
        // Given
        var message = "test.message";
        var expectedResult = "Test Message";
        Object[] args = { "arg1", "arg2" };

        // When
        when(messageSource.getMessage(eq(message), eq(args), any(Locale.class))).thenReturn(expectedResult);
        String result = messageService.get(message, args);

        // Then
        assertEquals(expectedResult, result);
        verify(messageSource).getMessage(eq(message), eq(args), any(Locale.class));
    }
}
