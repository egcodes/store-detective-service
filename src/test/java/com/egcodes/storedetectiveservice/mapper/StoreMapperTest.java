package com.egcodes.storedetectiveservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.egcodes.storedetectiveservice.api.dto.StoreDTO;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class StoreMapperTest {

    @InjectMocks
    private StoreMapper storeMapper = new StoreMapperImpl();

    @Test
    public void testToDTO() {
        // Given
        var store = new Store();
        store.setUuid("aed3cebe-e009-40f8-8a74-43e7bc05b5a4");
        store.setCity("Istanbul");
        store.setPostalCode("12345");

        // When
        var storeDTO = storeMapper.toDTO(store);

        // Then
        assertEquals("aed3cebe-e009-40f8-8a74-43e7bc05b5a4", storeDTO.getUuid());
        assertEquals("Istanbul", storeDTO.getCity());
        assertEquals("12345", storeDTO.getPostalCode());
    }

    @Test
    public void testToEntity() {
        // Given
        var storeDTO = new StoreDTO();
        storeDTO.setUuid("aed3cebe-e009-40f8-8a74-43e7bc05b5a4");
        storeDTO.setCity("Istanbul");
        storeDTO.setPostalCode("12345");

        // When
        var store = storeMapper.toEntity(storeDTO);

        // Then
        assertEquals("aed3cebe-e009-40f8-8a74-43e7bc05b5a4", store.getUuid());
        assertEquals("Istanbul", store.getCity());
        assertEquals("12345", store.getPostalCode());
    }

    @Test
    public void testToDTOs() {
        // Given
        var store1 = new Store();
        store1.setUuid("aed3cebe-e009-40f8-8a74-43e7bc05b5a4");
        store1.setCity("Istanbul");
        store1.setPostalCode("12345");

        var store2 = new Store();
        store2.setUuid("efgh5678");
        store2.setCity("Los Angeles");
        store2.setPostalCode("67890");

        var storeList = Arrays.asList(store1, store2);

        // When
        var storeDTOList = storeMapper.toDTOs(storeList);

        // Then
        assertEquals(2, storeDTOList.size());

        var storeDTO1 = storeDTOList.get(0);
        assertEquals("aed3cebe-e009-40f8-8a74-43e7bc05b5a4", storeDTO1.getUuid());
        assertEquals("Istanbul", storeDTO1.getCity());
        assertEquals("12345", storeDTO1.getPostalCode());

        var storeDTO2 = storeDTOList.get(1);
        assertEquals("efgh5678", storeDTO2.getUuid());
        assertEquals("Los Angeles", storeDTO2.getCity());
        assertEquals("67890", storeDTO2.getPostalCode());
    }
}
