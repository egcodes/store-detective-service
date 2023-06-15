package com.egcodes.storedetectiveservice.service;

import com.egcodes.storedetectiveservice.constants.Header;
import org.slf4j.MDC;

public class CorrelationIdHolder {

    public static void setCorrelationId(String correlationId) {
        MDC.put(Header.CORRELATION_ID_HEADER, correlationId);
    }

    public static String getCorrelationId() {
        return MDC.get(Header.CORRELATION_ID_HEADER);
    }

    public static void clearCorrelationId() {
        MDC.remove(Header.CORRELATION_ID_HEADER);
    }
}
