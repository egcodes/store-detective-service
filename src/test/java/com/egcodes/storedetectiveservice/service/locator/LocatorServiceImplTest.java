package com.egcodes.storedetectiveservice.service.locator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.egcodes.storedetectiveservice.TestContextProvider;
import com.egcodes.storedetectiveservice.api.dto.LocationDTO;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import com.egcodes.storedetectiveservice.service.store.StoreService;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Locator Service Tests")
public class LocatorServiceImplTest {

    @InjectMocks
    private LocatorServiceImpl locatorService;

    @Mock
    private StoreService storeService;


    @Test
    @DisplayName("Test if nearest stores are found based on given location")
    public void testFindNearestNStores() {
        // Given
        var locationDTO = TestContextProvider.createLocationDTO();
        var numberOfStores = 3;
        var mockStores = TestContextProvider.createStoreBy(numberOfStores);

        // When
        when(storeService.findAll()).thenReturn(mockStores);
        var nearestStores = locatorService.findNearestNStores(locationDTO, numberOfStores);


        // Then
        verify(storeService, times(1)).findAll();
        assertEquals(numberOfStores, nearestStores.size());
        assertEquals(mockStores, nearestStores);
    }

    @Test
    @DisplayName("When the nearer store is closed")
    public void findNearestNStoresByCurrentTime() {
        // Given
        var locationDTO = new LocationDTO(5.0, 5.0);
        var currentTime = LocalTime.of(10, 0);

        var store1 = new Store();
        store1.setLatitude("5");
        store1.setLongitude("5");
        store1.setTodayOpen("13:00");
        store1.setTodayClose("18:00");
        var store2 = new Store();
        store2.setLatitude("25");
        store2.setLongitude("25");
        store2.setTodayOpen("09:00");
        store2.setTodayClose("18:00");

        var mockStores = List.of(store1, store2);
        var numberOfStores = 1;

        var store1OpeningTime = LocalTime.of(LocalTime.parse(store1.getTodayOpen()).getHour(), 0);
        var store1ClosingTime = LocalTime.of(LocalTime.parse(store1.getTodayClose()).getHour(), 0);
        var store2OpeningTime = LocalTime.of(LocalTime.parse(store2.getTodayOpen()).getHour(), 0);

        try (var mocked = mockStatic(LocalTime.class)) {
            //When
            mocked.when(LocalTime::now).thenReturn(currentTime);
            mocked.when(() -> LocalTime.parse(store1.getTodayOpen())).thenReturn(store1OpeningTime);
            mocked.when(() -> LocalTime.parse(store1.getTodayClose())).thenReturn(store1ClosingTime);
            mocked.when(() -> LocalTime.parse(store2.getTodayOpen())).thenReturn(store2OpeningTime);

            when(storeService.findAll()).thenReturn(mockStores);
            var nearestStores = locatorService.findNearestNStoresByCurrentTime(locationDTO, numberOfStores);

            // Then
            verify(storeService, times(1)).findAll();
            assertEquals(numberOfStores, nearestStores.size());
            assertEquals(nearestStores.get(0), store2);
        }

    }

}
