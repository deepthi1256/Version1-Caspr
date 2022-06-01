package com.walmart.caspr.exception;

import com.walmart.caspr.model.HttpRequest;
import reactor.kafka.receiver.ReceiverRecord;

public class ReceiverRecordException extends RuntimeException {
    private final transient ReceiverRecord<String, HttpRequest> receiverRecord;

    public ReceiverRecordException(ReceiverRecord<String, HttpRequest> receiverRecord, Throwable t) {
        super(t);
        this.receiverRecord = receiverRecord;
    }

    public ReceiverRecord<String, HttpRequest> getRecord() {
        return this.receiverRecord;
    }
}
