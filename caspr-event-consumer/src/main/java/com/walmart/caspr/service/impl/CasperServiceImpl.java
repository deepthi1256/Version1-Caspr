package com.walmart.caspr.service.impl;

import com.walmart.caspr.model.HttpRequest;
import com.walmart.caspr.model.api.ResponseBody;
import com.walmart.caspr.service.CasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;
import reactor.util.function.Tuple2;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CasperServiceImpl implements CasperService {

    private final Logger log = Loggers.getLogger(CasperServiceImpl.class);
    @Autowired
    private WebClient webClient;

    @Override
    public Mono<ResponseBody> postPayloadToCasper(HttpRequest casperIncomingMessage, String message) {
        webClient = WebClient.create(casperIncomingMessage.getUrl());
        return Mono.just(casperIncomingMessage)
                .map(incomingMessage -> webClient.post()
                        .body(Mono.just(incomingMessage.getPayLoad()), String.class)
                        .headers(httpHeaders -> {
                            if (Objects.nonNull(incomingMessage.getHeaders())) {
                                httpHeaders.setAll(incomingMessage.getHeaders().entrySet()
                                        .stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue())));
                            }
                        })
                        .cookies(cookies -> {
                            if (Objects.nonNull(incomingMessage.getCookies())) {
                                cookies.setAll(incomingMessage.getCookies().entrySet()
                                        .stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue())));
                            }
                        })
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(ResponseBody.class)
                        .elapsed()
                        .doOnNext(tuple -> log.info("postPayloadToCasper:: time elapsed for Capsr API : {} ms .", tuple.getT1()))
                        .map(Tuple2::getT2)
                ).flatMap(responseBodyMono -> responseBodyMono)
                .doOnNext(responseBody -> log.info("postPayloadToCasper:: responseBody start date {} end date {}",
                        responseBody.getPayload() != null ? responseBody.getPayload().getStartDate() : "",
                        responseBody.getPayload() != null ? responseBody.getPayload().getEndDate() : ""));

    }
}
