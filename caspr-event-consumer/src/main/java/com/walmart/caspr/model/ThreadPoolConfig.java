package com.walmart.caspr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadPoolConfig {
    private int threadCap;
    private int queuedTaskCap;
    private String threadPrefix;
    private int ttlSeconds;
}
