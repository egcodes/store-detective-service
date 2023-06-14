package com.egcodes.storedetectiveservice.api.handler;

import com.egcodes.storedetectiveservice.exception.LocatorException;
import com.egcodes.storedetectiveservice.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(LocatorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleLocatorException(LocatorException ex) {
        return ErrorResponse.builder()
            .code(ex.getCode())
            .type(ex.getMessage())
            .message(messageService.get(ex.getMessage(), ex.getArgs()))
            .build();
    }

    /*
     * Http exceptions handlers
    */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        return ErrorResponse.builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .type(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(MethodArgumentNotValidException ex) {
        return createBadRequest(ex);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(MethodArgumentTypeMismatchException ex) {
        return createBadRequest(ex);
    }

    private ErrorResponse createBadRequest(Exception ex) {
        return ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .type(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }


}