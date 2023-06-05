package com.egcodes.storedetectiveservice.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StoreDTO {

    @JsonIgnore
    private String uuid;

    private String city;

    private String postalCode;

    private String street;

    private String street2;

    private String street3;

    private String addressName;

    private String longitude;

    private String latitude;

    @JsonIgnore
    private String complexNumber;

    private boolean showWarningMessage;

    private String locationType;

    private boolean collectionPoint;

    @JsonIgnore
    private String sapStoreID;

    private String todayOpen;

    private String todayClose;

    /***/

    private double distance;

}
