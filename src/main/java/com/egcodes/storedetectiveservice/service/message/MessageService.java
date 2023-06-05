package com.egcodes.storedetectiveservice.service.message;

public interface MessageService {

    String get(String message);

    String get(String message, Object[] args);

}
