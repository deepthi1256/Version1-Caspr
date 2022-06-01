package com.walmart.caspr.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class SlotDay {

    @JsonProperty("slotDate")
    private String slotDate;
    @JsonProperty("storeId")
    private String storeId;
    @JsonProperty("fulfillmentStoreId")
    private String fulfillmentStoreId;
    @JsonProperty("accessPointId")
    private String accessPointId;
    @JsonProperty("supportedTimezone")
    private String supportedTimezone;
    @JsonProperty("fulfillmentType")
    private String fulfillmentType;
    @JsonProperty("slots")
    private List<Object> slots = null;

}
