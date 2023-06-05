package com.egcodes.storedetectiveservice.service.locator;

import com.egcodes.storedetectiveservice.api.dto.LocationDTO;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import java.util.List;

public interface LocatorService {

    List<Store> findNearestNStores(LocationDTO locationDTO, int numberOfStores);

    List<Store> findNearestNStoresByCurrentTime(LocationDTO locationDTO, int numberOfStores);

}
