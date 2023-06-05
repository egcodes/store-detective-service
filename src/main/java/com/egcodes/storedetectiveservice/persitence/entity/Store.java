package com.egcodes.storedetectiveservice.persitence.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Store  implements Serializable {

    private static final long serialVersionUID = 5526679792415844339L;

    @Id
    private String uuid;
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
    private String longitude;
    private String latitude;
    private String complexNumber;
    private boolean showWarningMessage;
    private String todayOpen;
    private String locationType;
    private boolean collectionPoint;
    private String sapStoreID;
    private String todayClose;

    @Transient
    private double distance;

}