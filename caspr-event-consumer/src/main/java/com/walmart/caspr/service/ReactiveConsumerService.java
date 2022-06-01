package com.walmart.caspr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.caspr.exception.ReceiverRecordException;
import com.walmart.caspr.model.HttpRequest;
import com.walmart.caspr.model.ThreadPoolConfig;
import com.walmart.caspr.model.api.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.util.Logger;
import reactor.util.Loggers;
import reactor.util.retry.Retry;

@Service
public class ReactiveConsumerService {

    private final Logger log = Loggers.getLogger(ReactiveConsumerService.class);
    private final ReactiveKafkaConsumerTemplate<String, String> requestReactiveKafkaConsumerTemplate;

    private final ObjectMapper objectMapper;

    private final CasperService casperService;

    private final ValidatorService validatorService;

    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, String> requestReactiveKafkaConsumerTemplate,
                                   ObjectMapper objectMapper, CasperService casperService,
                                   ValidatorService validatorService
    ) {
        this.requestReactiveKafkaConsumerTemplate = requestReactiveKafkaConsumerTemplate;
        this.objectMapper = objectMapper;
        this.casperService = casperService;
        this.validatorService = validatorService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void processEvent() {
        log.info("processEvent:: started..");

        Scheduler scheduler = Schedulers.newBoundedElastic(threadPoolConfig.getThreadCap(),
                threadPoolConfig.getQueuedTaskCap(), threadPoolConfig.getThreadPrefix(), threadPoolConfig.getTtlSeconds());

        Flux<ReceiverRecord<String, String>> receiverRecordFlux = Flux.defer(requestReactiveKafkaConsumerTemplate::receive);
        receiverRecordFlux.groupBy(m -> m.receiverOffset().topicPartition())
                .doOnNext(partitionFlux -> log.info("processEvent:: topicPartition {}", partitionFlux.key()))
                .flatMap(partitionFlux -> partitionFlux.publishOn(scheduler)
                        .doOnNext(r -> log.info("processEvent:: Record received from offset {} from topicPartition {} with message key {}", r.receiverOffset().topicPartition(), r.key(), r.offset()))
                        .map(this::processRecord)
                        .doOnNext(receiverRecordInfo -> log.info("processEvent:: Record processed from offset {} from topicPartition {} with message key {}", receiverRecordInfo.receiverOffset().offset(), receiverRecordInfo.receiverOffset().topicPartition()))
                        .doOnError(error -> log.error("processEvent:: There is an exception while processing the message", error))
                        .retryWhen(Retry.max(3))
                        .onErrorResume(error -> {
                            ReceiverRecordException ex = (ReceiverRecordException) error.getCause();
                            log.error("processEvent:: Retries exhausted for the offset {} from topicPartition {} with message key {}", ex.getRecord().receiverOffset().offset(), ex.getRecord().receiverOffset().topicPartition(), ex.getRecord().key());

                            //Publish to error topic

                            ex.getRecord().receiverOffset().acknowledge();

                            return Mono.empty();
                        })
                )
                .subscribe(
                        key -> log.info("Successfully consumed messages, key {}", key),
                        error -> log.error("Error while consuming messages ", error));
    }

    private ReceiverRecord<String, String> processRecord(ReceiverRecord<String, String> receiverRecord) {
        log.info("processRecord:: started..");
        try {
            HttpRequest httpRequest = objectMapper.readValue(receiverRecord.value(), HttpRequest.class);
            ResponseBody validatorPayLoad = objectMapper.readValue(httpRequest.getValidator().getPayLoad(), ResponseBody.class);
            Mono<ResponseBody> apiResponse = casperService.postPayloadToCasper(httpRequest, receiverRecord.value());
            boolean result = validatorService.validateResponses(apiResponse.block(), validatorPayLoad);
            if (Boolean.compare(result, false) == 0) {
                log.info("Payload are not the same for message: {}", receiverRecord.value());
                //Publish Errors
            }
        } catch (JsonMappingException e) {
            log.error("Error While Json Mapping : ", e);
        } catch (JsonProcessingException e) {
            log.error("Error While Json Processing : ", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Error while processing payload : ", e);
        }
        receiverRecord.receiverOffset().acknowledge();
        log.info("processRecord:: offset {} from topicPartition {} is acknowledged successfully", receiverRecord.receiverOffset().offset(), receiverRecord.receiverOffset().topicPartition());
        return receiverRecord;
    }

}
