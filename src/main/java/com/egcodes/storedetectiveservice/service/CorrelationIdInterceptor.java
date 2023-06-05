package com.egcodes.storedetectiveservice.service;

import static com.egcodes.storedetectiveservice.constants.Header.CORRELATION_ID_HEADER;
import static java.util.Objects.isNull;

import java.util.UUID;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorrelationIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        var correlationId = request.getHeader(CORRELATION_ID_HEADER);

        if (isNull(correlationId) || correlationId.isEmpty()) {
            correlationId = generateCorrelationId();
        }

        CorrelationIdHolder.setCorrelationId(correlationId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CorrelationIdHolder.clearCorrelationId();
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}