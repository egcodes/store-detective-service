package com.egcodes.storedetectiveservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.egcodes.storedetectiveservice.persitence.entity.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StoreTest Test")
public class StoreTest {

    @Test
    @DisplayName("Test all getter and setter")
    public void testGettersAndSetters() {
        var store = new Store();
        store.setUuid("aed3cebe-e009-40f8-8a74-43e7bc05b5a4");
        store.setCity("Amersfoort");
        store.setPostalCode("3824 EE");
        store.setStreet("Zonnewijzer");
        store.setStreet2("21");
        store.setStreet3("");
        store.setAddressName("Jumbo Amersfoort Den Blanken Nieuwland");
        store.setLongitude("5.376590");
        store.setLatitude("52.199279");
        store.setComplexNumber("33021");
        store.setShowWarningMessage(true);
        store.setLocationType("07:00");
        store.setCollectionPoint(true);
        store.setSapStoreID("3kIKYx4XYocAAAFNDH47frdc");
        store.setTodayOpen("22:00");
        store.setTodayClose("SupermarktPuP");
        store.setDistance(3426);

        assertEquals("aed3cebe-e009-40f8-8a74-43e7bc05b5a4", store.getUuid());
        assertEquals("Amersfoort", store.getCity());
        assertEquals("3824 EE", store.getPostalCode());
        assertEquals("Zonnewijzer", store.getStreet());
        assertEquals("21", store.getStreet2());
        assertEquals("", store.getStreet3());
        assertEquals("Jumbo Amersfoort Den Blanken Nieuwland", store.getAddressName());
        assertEquals("5.376590", store.getLongitude());
        assertEquals("52.199279", store.getLatitude());
        assertEquals("33021", store.getComplexNumber());
        assertTrue(store.isShowWarningMessage());
        assertEquals("07:00", store.getLocationType());
        assertTrue(store.isCollectionPoint());
        assertEquals("3kIKYx4XYocAAAFNDH47frdc", store.getSapStoreID());
        assertEquals("22:00", store.getTodayOpen());
        assertEquals("SupermarktPuP", store.getTodayClose());
        assertEquals(3426, store.getDistance());
    }
}