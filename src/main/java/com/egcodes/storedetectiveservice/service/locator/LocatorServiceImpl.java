package com.egcodes.storedetectiveservice.service.locator;

import static com.egcodes.storedetectiveservice.constants.Constants.STORE_OPEN;
import static java.util.Objects.requireNonNull;

import com.egcodes.storedetectiveservice.api.dto.LocationDTO;
import com.egcodes.storedetectiveservice.exception.LocatorException;
import com.egcodes.storedetectiveservice.exception.errorcode.ErrorCodes;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import com.egcodes.storedetectiveservice.service.store.StoreService;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocatorServiceImpl implements LocatorService {

    private final StoreService storeService;

    @Override
    public List<Store> findNearestNStores(LocationDTO locationDTO, int numberOfStores) {
        requireNonNull(locationDTO, ErrorCodes.LOCATION_CANT_BE_EMPTY.getMessage());

        log.info("Attempting to find {} nearest stores for location latitude: {}, longitude: {}",
            numberOfStores, locationDTO.getLatitude(), locationDTO.getLongitude());

        var stores = findStoresBy(storeService.findAll().parallelStream(), locationDTO, numberOfStores);

        log.debug("Found {} nearest stores for location latitude: {}, longitude: {}",
            stores.size(), locationDTO.getLatitude(), locationDTO.getLongitude());
        return stores;
    }

    @Override
    public List<Store> findNearestNStoresByCurrentTime(LocationDTO locationDTO, int numberOfStores) {
        requireNonNull(locationDTO, ErrorCodes.LOCATION_CANT_BE_EMPTY.getMessage());

        log.info("Attempting to find {} nearest stores open at current time for location latitude: {}, longitude: {}",
            numberOfStores, locationDTO.getLatitude(), locationDTO.getLongitude());

        var filteredStoreStream = storeService.findAll().parallelStream()
            .filter(this::isOpenAtCurrentTime);

        var stores = findStoresBy(filteredStoreStream, locationDTO, numberOfStores);

        log.debug("Found {} nearest stores open at current time for location latitude: {}, longitude: {}",
            stores.size(), locationDTO.getLatitude(), locationDTO.getLongitude());
        return stores;
    }

    private List<Store> findStoresBy(Stream<Store> storeStream, LocationDTO locationDTO, int numberOfStores) {
        return storeStream
            .peek(store -> {
                double distance = findDistanceBy(locationDTO, store);
                log.debug("Calculated distance: {} for store: {} at location latitude: {}, longitude: {}",
                    distance, store.getUuid(), locationDTO.getLatitude(), locationDTO.getLongitude());
                store.setDistance(distance);
            })
            .sorted(Comparator.comparingDouble(Store::getDistance))
            .limit(numberOfStores)
            .collect(Collectors.toList());
    }

    private double findDistanceBy(LocationDTO locationDTO, Store store) {
        double distance;
        try {
            log.trace("Calculating distance for store: {}, [latitude: {}, longitude: {}]", store.getUuid(), store.getLatitude(), store.getLongitude());

            distance = calcDistance(Double.parseDouble(store.getLatitude()), Double.parseDouble(store.getLongitude()),
                locationDTO.getLatitude(), locationDTO.getLongitude());

            log.trace("Distance calculated for store: {}, {}", store.getUuid(), distance);
        } catch (Exception e) {
            log.error("Error calculating distance: {}", e.getMessage());
            throw new LocatorException(ErrorCodes.GENERAL_LOCATOR_EXCEPTION, new String[]{locationDTO.toString()});
        }

        return distance;
    }

    private boolean isOpenAtCurrentTime(Store store) {
        if (store.getTodayOpen().equals(STORE_OPEN))
            return true;

        var currentTime = LocalTime.now();
        var openingTime = LocalTime.parse(store.getTodayOpen());
        var closingTime = LocalTime.parse(store.getTodayClose());

        var isOpen = currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);

        log.trace("Store: {} is {} at current time: {}", store.getUuid(), isOpen ? "open" : "closed", currentTime);

        return isOpen;
    }

    private double calcDistanceRoundly(double sourceLat, double sourceLon, double targetLat, double targetLon) {
        double latDifference = Math.abs(sourceLat - targetLat);
        double lonDifference = Math.abs(sourceLon - targetLon);

        // Each degree of latitude or longitude is approximately 111 kilometers
        double latDistance = latDifference * 111;
        double lonDistance = lonDifference * 111;

        return Math.round(Math.sqrt(Math.pow(latDistance, 2) + Math.pow(lonDistance, 2)) * 100) / 100.0;
    }

    private double calcDistance(double sourceLat, double sourceLon, double targetLat, double targetLon) {
        sourceLat = Math.toRadians(sourceLat);
        targetLat = Math.toRadians(targetLat);
        sourceLon = Math.toRadians(sourceLon);
        targetLon = Math.toRadians(targetLon);

        // Haversine formula
        var dLat = targetLat - sourceLat;
        var dLon = targetLon - sourceLon;
        var a = Math.pow(Math.sin(dLat / 2), 2)
            + Math.cos(sourceLat) * Math.cos(targetLat)
            * Math.pow(Math.sin(dLon / 2), 2);

        var c = 2 * Math.asin(Math.sqrt(a));
        var r = 6371; // Radius of earth in kilometers is 6371, use 3956 for miles
        return Math.round(r * c * 100.0) / 100.0;
    }

}