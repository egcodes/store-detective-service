package com.egcodes.storedetectiveservice.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    @JsonInclude(Include.NON_NULL)
    private String corrId;
    private int code;
    private String type;
    private String message;

}
