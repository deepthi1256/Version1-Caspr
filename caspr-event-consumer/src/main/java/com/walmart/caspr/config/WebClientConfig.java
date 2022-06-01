package com.walmart.caspr.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
    @Bean(name = "casperWebClient")
    public WebClient webClient(@Qualifier("casperReactorClientHttpConnector") ReactorClientHttpConnector connector) {
        return WebClient.builder().clientConnector(connector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean(name = "casperReactorClientHttpConnector")
    public ReactorClientHttpConnector reactorClientHttpConnector(@Qualifier("casperHttpClient") HttpClient httpClient) {
        return new ReactorClientHttpConnector(httpClient);
    }

    @Bean(name = "casperHttpClient")
    public HttpClient casperHttpClient() {
        return HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(3, TimeUnit.MINUTES));
                    connection.addHandlerLast(new WriteTimeoutHandler(3, TimeUnit.MINUTES));

                });
    }
}
