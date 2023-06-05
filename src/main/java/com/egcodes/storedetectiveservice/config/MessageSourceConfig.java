package com.egcodes.storedetectiveservice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
            "classpath:/messages/api_error_messages",
            "classpath:/messages/api_response_messages"
        );
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
