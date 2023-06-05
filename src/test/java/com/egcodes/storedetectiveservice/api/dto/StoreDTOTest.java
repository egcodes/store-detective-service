package com.egcodes.storedetectiveservice.api.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("StoreDTOTest Test")
public class StoreDTOTest {

    @Test
    @DisplayName("Test all getter and setter")
    public void testGettersAndSetters() {
        var storeDTO = new StoreDTO();
        storeDTO.setUuid("aed3cebe-e009-40f8-8a74-43e7bc05b5a4");
        storeDTO.setCity("Amersfoort");
        storeDTO.setPostalCode("3824 EE");
        storeDTO.setStreet("Zonnewijzer");
        storeDTO.setStreet2("21");
        storeDTO.setStreet3("");
        storeDTO.setAddressName("Jumbo Amersfoort Den Blanken Nieuwland");
        storeDTO.setLongitude("5.376590");
        storeDTO.setLatitude("52.199279");
        storeDTO.setComplexNumber("33021");
        storeDTO.setShowWarningMessage(true);
        storeDTO.setLocationType("07:00");
        storeDTO.setCollectionPoint(true);
        storeDTO.setSapStoreID("3kIKYx4XYocAAAFNDH47frdc");
        storeDTO.setTodayOpen("22:00");
        storeDTO.setTodayClose("SupermarktPuP");
        storeDTO.setDistance(3426);

        assertEquals("aed3cebe-e009-40f8-8a74-43e7bc05b5a4", storeDTO.getUuid());
        assertEquals("Amersfoort", storeDTO.getCity());
        assertEquals("3824 EE", storeDTO.getPostalCode());
        assertEquals("Zonnewijzer", storeDTO.getStreet());
        assertEquals("21", storeDTO.getStreet2());
        assertEquals("", storeDTO.getStreet3());
        assertEquals("Jumbo Amersfoort Den Blanken Nieuwland", storeDTO.getAddressName());
        assertEquals("5.376590", storeDTO.getLongitude());
        assertEquals("52.199279", storeDTO.getLatitude());
        assertEquals("33021", storeDTO.getComplexNumber());
        assertTrue(storeDTO.isShowWarningMessage());
        assertEquals("07:00", storeDTO.getLocationType());
        assertTrue(storeDTO.isCollectionPoint());
        assertEquals("3kIKYx4XYocAAAFNDH47frdc", storeDTO.getSapStoreID());
        assertEquals("22:00", storeDTO.getTodayOpen());
        assertEquals("SupermarktPuP", storeDTO.getTodayClose());
        assertEquals(3426, storeDTO.getDistance());
    }

}