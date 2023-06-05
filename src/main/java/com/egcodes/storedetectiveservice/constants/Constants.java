package com.egcodes.storedetectiveservice.constants;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Constants {

    public static final String CACHE_FOR_STORES = "stores";

    public static final String STORE_OPEN = "Gesloten";

    public static final int TIME_LIMIT_FOR_INFO = 250; //ms
    public static final int TIME_LIMIT_FOR_WARN = 500; //ms

}
