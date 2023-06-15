package com.egcodes.storedetectiveservice.service;

import static com.egcodes.storedetectiveservice.constants.Constants.TIME_LIMIT_FOR_INFO;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

import java.io.IOException;
import java.time.Duration;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
public class StatsFilterTest {

    @InjectMocks
    private StatsFilter statsFilter;

    @Test
    @DisplayName("Given a servlet request, when filter is applied, then log output should contain request URI and time")
    void doFilter(CapturedOutput output) throws IOException, ServletException {
        // Given
        var request = new MockHttpServletRequest("GET", "/test");
        var response = new MockHttpServletResponse();
        var filterChain = mock(FilterChain.class);

        var testDuration = Duration.ofMillis(TIME_LIMIT_FOR_INFO + 1);
        try (var mocked = mockStatic(Duration.class)) {
            // When
            mocked.when(() -> Duration.between(any(), any())).thenReturn(testDuration);
            statsFilter.doFilter(request, response, filterChain);

            // Then
            var logOutput = output.toString();
            assertTrue(logOutput.contains(testDuration.toMillis() + " ms"));
        }

    }
}
