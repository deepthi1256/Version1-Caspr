package com.walmart.caspr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Headers {

    @JsonProperty("content-length")
    private String contentLength;
    @JsonProperty("x-o-segment")
    private String xOSegment;
    @JsonProperty("wm_qos.correlation_id")
    private String wmQosCorrelationId;
    @JsonProperty("wm_consumer.tenant_id")
    private String wmConsumerTenantId;
    @JsonProperty("postman-token")
    private String postmanToken;
    @JsonProperty("wm_svc.version")
    private String wmSvcVersion;
    @JsonProperty("wm_sec.key_version")
    private String wmSecKeyVersion;
    @JsonProperty("wm_svc.name")
    private String wmSvcName;
    @JsonProperty("wm_consumer.id")
    private String wmConsumerId;
    @JsonProperty("accept")
    private String accept;
    @JsonProperty("wm_consumer.intimestamp")
    private String wmConsumerIntimestamp;
    @JsonProperty("wm_sec.auth_signature")
    private String wmSecAuthSignature;
    @JsonProperty("host")
    private String host;
    @JsonProperty("connection")
    private String connection;
    @JsonProperty("content-type")
    private String contentType;
    @JsonProperty("cache-control")
    private String cacheControl;
    @JsonProperty("wm_svc.env")
    private String wmSvcEnv;
    @JsonProperty("accept-encoding")
    private String acceptEncoding;
    @JsonProperty("user-agent")
    private String userAgent;
}
