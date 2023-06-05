package com.egcodes.storedetectiveservice.persitence.resource;

import com.egcodes.storedetectiveservice.model.store.Stores;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Slf4j
public class StoreFileRepository {

    public List<Store> stores = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void populate() {
        var objectMapper = new ObjectMapper();
        var storeFileName = "data/stores.json";

        try {
            var storesFromJson = objectMapper.readValue(new ClassPathResource(storeFileName).getInputStream(), Stores.class);
            setStores(storesFromJson.getStores());
        } catch (IOException e) {
            log.error("The file containing store information could not be found: {}", storeFileName);
        }
    }

}
