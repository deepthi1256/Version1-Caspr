package com.walmart.caspr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Validator {
    @JsonProperty("payLoad")
    private String payLoad;
}
