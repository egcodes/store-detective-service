package com.egcodes.storedetectiveservice.service.store;

import static com.egcodes.storedetectiveservice.constants.Constants.CACHE_FOR_STORES;

import com.egcodes.storedetectiveservice.persitence.entity.Store;
import com.egcodes.storedetectiveservice.persitence.repository.StoreRepository;
import com.egcodes.storedetectiveservice.persitence.resource.StoreFileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreFileRepository storeFileRepository;

    @Override
    @Cacheable(CACHE_FOR_STORES)
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @CacheEvict(value = CACHE_FOR_STORES, allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.storesTTL}")
    public void evictStores() {
        log.debug("Evict {} data from cache", CACHE_FOR_STORES);
    }

    @Override
    public List<Store> findAllFromFile() {
        return storeFileRepository.getStores();
    }


}