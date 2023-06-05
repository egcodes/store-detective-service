package com.egcodes.storedetectiveservice.config;

import com.egcodes.storedetectiveservice.service.CorrelationIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorrelationIdConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorrelationIdInterceptor());
    }

}
