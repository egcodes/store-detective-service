package com.egcodes.storedetectiveservice.api.controller;

import static com.egcodes.storedetectiveservice.constants.Constants.MAX_LAT;
import static com.egcodes.storedetectiveservice.constants.Constants.MAX_LON;
import static com.egcodes.storedetectiveservice.constants.Constants.MIN_LAT;
import static com.egcodes.storedetectiveservice.constants.Constants.MIN_LON;

import com.egcodes.storedetectiveservice.api.dto.LocationDTO;
import com.egcodes.storedetectiveservice.api.dto.StoreDTO;
import com.egcodes.storedetectiveservice.mapper.StoreMapper;
import com.egcodes.storedetectiveservice.service.locator.LocatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stores")
@Api(tags = "Stores API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LocatorController {

    private final LocatorService locatorService;
    private final StoreMapper storeMapper;

    @ApiOperation(value = "Find the nearest stores", notes = "Find N nearest stores with given location and number")
    @GetMapping(value = "/nearest/{numberOfStores}")
    public ResponseEntity<List<StoreDTO>> findNearestNStores(
        @ApiParam(value = "Latitude must be between -90.0 and 90.0", example = "51.778461") @Min(MIN_LAT) @Max(MAX_LAT) @RequestParam double latitude,
        @ApiParam(value = "Longitude must be between -180.0 and 180.0", example = "4.615551") @Min(MIN_LON) @Max(MAX_LON) @RequestParam double longitude,
        @ApiParam(value = "Number of Stores", example = "3") @PathVariable @Max(10) int numberOfStores)
    {
        log.info("Received request to find {} nearest stores for location latitude: {}, longitude: {}",
            numberOfStores, latitude, longitude);

        var locationDTO = new LocationDTO(latitude, longitude);
        var stores = storeMapper.toDTOs(locatorService.findNearestNStores(locationDTO, numberOfStores));

        log.debug("Found {} nearest stores for location latitude: {}, longitude: {}",
            stores.size(), latitude, longitude);

        return ResponseEntity.ok(stores);
    }

    @ApiOperation(value = "Find the nearest stores by current time", notes = "Find N nearest stores with given location by current time")
    @GetMapping(value = "/nearestByCurrentTime/{numberOfStores}")
    public ResponseEntity<List<StoreDTO>> findNearestNStoresByCurrentTime(
        @ApiParam(value = "Latitude must be between -90.0 and 90.0", example = "51.778461") @Min(MIN_LAT) @Max(MAX_LAT) @RequestParam double latitude,
        @ApiParam(value = "Longitude must be between -180.0 and 180.0", example = "4.615551") @Min(MIN_LON) @Max(MAX_LON) @RequestParam double longitude,
        @ApiParam(value = "Number of Stores", example = "3") @PathVariable @Max(10) int numberOfStores)
    {
        log.info("Received request to find {} nearest stores open at current time for location latitude: {}, longitude: {}",
            numberOfStores, latitude, longitude);

        var locationDTO = new LocationDTO(latitude, longitude);
        var stores = storeMapper.toDTOs(locatorService.findNearestNStoresByCurrentTime(locationDTO, numberOfStores));

        log.debug("Found {} nearest stores open at current time for location latitude: {}, longitude: {}",
            stores.size(), latitude, longitude);

        return ResponseEntity.ok(stores);
    }

}