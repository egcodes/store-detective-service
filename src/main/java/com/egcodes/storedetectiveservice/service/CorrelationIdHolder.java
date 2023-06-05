package com.egcodes.storedetectiveservice.service;

public class CorrelationIdHolder {
    private static final ThreadLocal<String> correlationIdHolder = new ThreadLocal<>();

    public static void setCorrelationId(String correlationId) {
        correlationIdHolder.set(correlationId);
    }

    public static String getCorrelationId() {
        return correlationIdHolder.get();
    }

    public static void clearCorrelationId() {
        correlationIdHolder.remove();
    }
}
