package com.egcodes.storedetectiveservice.service.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.egcodes.storedetectiveservice.TestContextProvider;
import com.egcodes.storedetectiveservice.persitence.repository.StoreRepository;
import com.egcodes.storedetectiveservice.persitence.resource.StoreFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@DisplayName("Store Service Tests")
public class StoreServiceImplTest {

    @InjectMocks
    private StoreServiceImpl storeService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreFileRepository storeFileRepository;

    @Test
    @DisplayName("Should return all stores from the repository")
    public void testFindAll() {
        // Given
        var expectedStores = TestContextProvider.createStoreBy(3);

        // When
        when(storeRepository.findAll()).thenReturn(expectedStores);
        var actualStores = storeService.findAll();

        // Then
        assertEquals(expectedStores, actualStores);
    }

    @Test
    @DisplayName("Should return all stores from the file repository")
    public void testFindAllFromFile() {
        // Given
        var expectedStores = TestContextProvider.createStoreBy(3);

        // When
        when(storeFileRepository.getStores()).thenReturn(expectedStores);
        var actualStores = storeService.findAllFromFile();

        // Then
        assertEquals(expectedStores, actualStores);
    }
}
