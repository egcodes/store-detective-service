package com.egcodes.storedetectiveservice.config;

import static com.egcodes.storedetectiveservice.constants.Constants.CACHE_FOR_STORES;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CACHE_FOR_STORES);
    }

}
