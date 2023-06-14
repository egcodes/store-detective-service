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

    public static final int MIN_LAT = -90;
    public static final int MAX_LAT = 90;
    public static final int MIN_LON = -180;
    public static final int MAX_LON = 180;

}
