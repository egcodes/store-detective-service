package com.egcodes.storedetectiveservice.model.store;

import com.egcodes.storedetectiveservice.persitence.entity.Store;
import java.util.List;
import lombok.Data;

@Data
public class Stores {

    private List<Store> stores;
    private Attributes attributes;

}
