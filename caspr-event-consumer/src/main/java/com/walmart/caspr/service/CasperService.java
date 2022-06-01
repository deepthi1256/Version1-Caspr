package com.walmart.caspr.service;


import com.walmart.caspr.model.HttpRequest;
import com.walmart.caspr.model.api.ResponseBody;
import reactor.core.publisher.Mono;

public interface CasperService {

    Mono<ResponseBody> postPayloadToCasper(HttpRequest convertMessageToPojo, String message);

}
