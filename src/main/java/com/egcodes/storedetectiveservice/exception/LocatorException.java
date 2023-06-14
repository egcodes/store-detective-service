package com.egcodes.storedetectiveservice.exception;

import com.egcodes.storedetectiveservice.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public class LocatorException extends RuntimeException {

    private final int code;
    private final Object[] args;

    public LocatorException(ErrorCode errorCode, Object[] args) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.args = args;
    }

    public LocatorException(ErrorCode errorCode) {
        this(errorCode, new Object[]{});
    }

}
