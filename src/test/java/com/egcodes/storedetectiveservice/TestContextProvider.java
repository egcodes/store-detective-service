package com.egcodes.storedetectiveservice;

import com.egcodes.storedetectiveservice.api.dto.LocationDTO;
import com.egcodes.storedetectiveservice.api.dto.StoreDTO;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestContextProvider {

    public static LocationDTO createLocationDTO() {
        return new LocationDTO(51.778461, 4.615551);
    }

    public static LocationDTO createLocationDTO(Store store) {
        return new LocationDTO(Double.parseDouble(store.getLatitude()), Double.parseDouble(store.getLongitude()));
    }

    public static Store createStore() {
        var store = new Store();
        store.setLatitude("51.778461");
        store.setLongitude("4.615551");
        return store;
    }

    public static List<Store> createStoreBy(int numberOfStore) {
        return IntStream.range(0, numberOfStore)
            .mapToObj(i -> createStore())
            .collect(Collectors.toList());
    }

    public static StoreDTO createStoreDTO() {
        var storeDTO = new StoreDTO();
        storeDTO.setLatitude("51.778461");
        storeDTO.setLongitude("4.615551");
        storeDTO.setDistance(0.0);
        return storeDTO;
    }

    public static List<StoreDTO> createStoreDTOBy(int numberOfStore) {
        return IntStream.range(0, numberOfStore)
            .mapToObj(i -> createStoreDTO())
            .collect(Collectors.toList());
    }
}
