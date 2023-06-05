package com.egcodes.storedetectiveservice.exception;

import com.egcodes.storedetectiveservice.exception.errorcode.ErrorCode;
import com.egcodes.storedetectiveservice.service.CorrelationIdHolder;
import lombok.Getter;

@Getter
public class LocatorException extends RuntimeException {

    private final int code;
    private final String corrId;

    public LocatorException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.corrId = CorrelationIdHolder.getCorrelationId();
    }

}
