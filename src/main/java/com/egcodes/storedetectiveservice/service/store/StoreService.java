package com.egcodes.storedetectiveservice.service.store;

import com.egcodes.storedetectiveservice.persitence.entity.Store;
import java.util.List;

public interface StoreService {

    List<Store> findAll();

    List<Store> findAllFromFile();

}
