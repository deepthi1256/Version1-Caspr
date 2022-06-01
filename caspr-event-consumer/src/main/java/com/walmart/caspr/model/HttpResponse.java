package com.walmart.caspr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponse {
    private String status;
    private String exception;
    private Long timeTaken;
}
