package com.walmart.caspr.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Payload {

    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("supportExpress")
    private Boolean supportExpress;
    @JsonProperty("supportedSlotTypes")
    private List<String> supportedSlotTypes = null;
    @JsonProperty("slotDays")
    private List<SlotDay> slotDays = null;
    @JsonProperty("accessPointDetails")
    private List<AccessPointDetail> accessPointDetails = null;
    @JsonProperty("serviceInfo")
    private ServiceInfo serviceInfo;

}
