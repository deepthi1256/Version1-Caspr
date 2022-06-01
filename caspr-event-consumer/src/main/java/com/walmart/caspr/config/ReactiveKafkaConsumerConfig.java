package com.walmart.caspr.config;

import com.walmart.caspr.model.ThreadPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.Collections;

@Configuration
public class ReactiveKafkaConsumerConfig {
    private final Logger log = Loggers.getLogger(ReactiveKafkaConsumerConfig.class);

    @Bean
    public ReceiverOptions<String, String> kafkaReceiverOptions(@Value(value = "${spring.kafka.topic.name}") String topic, KafkaProperties kafkaProperties) {
        ReceiverOptions<String, String> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicReceiverOptions.subscription(Collections.singletonList(topic))
                .addAssignListener(receiverPartitions -> log.debug("onPartitionAssigned {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.debug("onPartitionsRevoked {}", receiverPartitions));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate(ReceiverOptions<String, String> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }

    @Bean
    @ConfigurationProperties("spring.kafka.consumer-thread-pool-config")
    public ThreadPoolConfig threadPoolConfig() {
        return new ThreadPoolConfig();
    }
}
