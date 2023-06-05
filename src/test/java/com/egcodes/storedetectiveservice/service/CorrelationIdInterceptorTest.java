package com.egcodes.storedetectiveservice.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CorrelationIdInterceptorTest {

    @InjectMocks
    private CorrelationIdInterceptor interceptor;

    @Test
    public void testPreHandle() {
        // Given
        var request = Mockito.mock(HttpServletRequest.class);
        var response = Mockito.mock(HttpServletResponse.class);
        var handler = new Object();

        // When
        var result = interceptor.preHandle(request, response, handler);

        // Then
        assertNotNull(CorrelationIdHolder.getCorrelationId());
        assertTrue(result);
    }

    @Test
    public void testAfterCompletion() {
        // Given
        var corrId = "aed3cebe-e009-40f8-8a74-43e7bc05b5a4";
        var request = Mockito.mock(HttpServletRequest.class);
        var response = Mockito.mock(HttpServletResponse.class);
        var handler = new Object();

        // When
        CorrelationIdHolder.setCorrelationId(corrId);

        // Then
        assertEquals(corrId, CorrelationIdHolder.getCorrelationId());
        assertDoesNotThrow(() -> interceptor.afterCompletion(request, response, handler, null));
        assertNull(CorrelationIdHolder.getCorrelationId());
    }
}
