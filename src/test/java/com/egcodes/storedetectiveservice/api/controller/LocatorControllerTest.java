package com.egcodes.storedetectiveservice.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.egcodes.storedetectiveservice.TestContextProvider;
import com.egcodes.storedetectiveservice.api.dto.LocationDTO;
import com.egcodes.storedetectiveservice.api.dto.StoreDTO;
import com.egcodes.storedetectiveservice.mapper.StoreMapper;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import com.egcodes.storedetectiveservice.service.locator.LocatorService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@DisplayName("Locator Controller Unit Tests")
@ExtendWith(MockitoExtension.class)
public class LocatorControllerTest {

    @InjectMocks
    private LocatorController locatorController;

    @Mock
    private LocatorService locatorService;

    @Mock
    private StoreMapper storeMapper;

    int numberOfStores;
    LocationDTO locationDTO;
    List<Store> mockStores;
    List<StoreDTO> mockStoreDTOs;

    @BeforeEach
    public void setUp() {
        numberOfStores = 3;
        locationDTO = TestContextProvider.createLocationDTO();
        mockStores = TestContextProvider.createStoreBy(numberOfStores);
        mockStoreDTOs = TestContextProvider.createStoreDTOBy(numberOfStores);
    }

    @Test
    @DisplayName("Should return the nearest stores when finding N nearest stores")
    public void testFindNearestNStores() {
        // When
        when(locatorService.findNearestNStores(locationDTO, numberOfStores)).thenReturn(mockStores);
        when(storeMapper.toDTOs(mockStores)).thenReturn(mockStoreDTOs);
        var response = locatorController.findNearestNStores(
            locationDTO.getLatitude(), locationDTO.getLongitude(),numberOfStores);

        // Then
        verify(locatorService, times(1)).findNearestNStores(locationDTO, numberOfStores);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockStoreDTOs, response.getBody());
    }

    @Test
    @DisplayName("Should return the nearest stores when finding N nearest stores by current time")
    public void testFindNearestNStoresByCurrentTime() {
        // When
        when(locatorService.findNearestNStoresByCurrentTime(locationDTO, numberOfStores)).thenReturn(mockStores);
        when(storeMapper.toDTOs(mockStores)).thenReturn(mockStoreDTOs);
        var response = locatorController.findNearestNStoresByCurrentTime(
            locationDTO.getLatitude(), locationDTO.getLongitude(),numberOfStores);

        // Then
        verify(locatorService, times(1)).findNearestNStoresByCurrentTime(locationDTO, numberOfStores);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockStoreDTOs, response.getBody());
    }

}
