package com.walmart.caspr.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ServiceAddress {

    @JsonProperty("isApoFpo")
    private String isApoFpo;
    @JsonProperty("isPoBox")
    private String isPoBox;
    @JsonProperty("country")
    private String country;
    @JsonProperty("geoPoint")
    private GeoPoint geoPoint;
    @JsonProperty("city")
    private String city;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("addressLineOne")
    private String addressLineOne;
    @JsonProperty("stateOrProvinceName")
    private String stateOrProvinceName;
    @JsonProperty("state")
    private String state;

}
