package com.walmart.caspr.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class AccessPointDetail {

    @JsonProperty("accessPointId")
    private String accessPointId;
    @JsonProperty("accessPointName")
    private String accessPointName;
    @JsonProperty("supportedTimezone")
    private String supportedTimezone;
    @JsonProperty("locationInstructions")
    private String locationInstructions;
    @JsonProperty("minimumPurchase")
    private String minimumPurchase;
    @JsonProperty("storeId")
    private String storeId;
    @JsonProperty("fulfillmentStoreId")
    private String fulfillmentStoreId;
    @JsonProperty("serviceAddress")
    private ServiceAddress serviceAddress;
    @JsonProperty("isPilotTraining")
    private Boolean isPilotTraining;
    @JsonProperty("isTest")
    private Boolean isTest;
    @JsonProperty("isCrowdSourced")
    private Boolean isCrowdSourced;
    @JsonProperty("acceptsEbtYN")
    private String acceptsEbtYN;
    @JsonProperty("ebtPayInPerson")
    private String ebtPayInPerson;
    @JsonProperty("allowAgeRestricted")
    private Boolean allowAgeRestricted;
    @JsonProperty("allowAlcohol")
    private Boolean allowAlcohol;


}
