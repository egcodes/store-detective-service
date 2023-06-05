package com.egcodes.storedetectiveservice.exception.errorcode;

import java.io.Serializable;

public class ErrorCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int code;
    private final String message;

    public ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
