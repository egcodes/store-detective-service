package com.egcodes.storedetectiveservice.api.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDTO {

    @ApiModelProperty(notes = "Latitude", example = "51.778461", required = true)
    @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90.0")
    @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90.0")
    @NotNull(message = "Latitude must be between -90.0 and 90.0")
    private double latitude;

    @ApiModelProperty(notes = "Longitude", example = "4.615551", required = true)
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180.0")
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180.0")
    @NotNull(message = "Longitude must be between -180.0 and 180.0")
    private double longitude;

}