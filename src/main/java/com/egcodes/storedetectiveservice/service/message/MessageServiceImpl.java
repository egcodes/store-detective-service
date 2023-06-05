package com.egcodes.storedetectiveservice.service.message;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;

    @Override
    public String get(String message) {
        return get(message, null);
    }

    @Override
    public String get(String message, Object[] args) {
        return messageSource.getMessage(message, args, Locale.getDefault());
    }

}
