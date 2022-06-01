package com.walmart.caspr.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ServiceInfo {

    @JsonProperty("fulfillmentType")
    private String fulfillmentType;
    @JsonProperty("reservationType")
    private String reservationType;
    @JsonProperty("dispenseType")
    private String dispenseType;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("fulfillmentOption")
    private String fulfillmentOption;
    @JsonProperty("locationType")
    private String locationType;
    @JsonProperty("accessType")
    private String accessType;

}
