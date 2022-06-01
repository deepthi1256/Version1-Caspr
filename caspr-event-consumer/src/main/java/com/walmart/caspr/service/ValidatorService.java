package com.walmart.caspr.service;

import com.walmart.caspr.model.api.ResponseBody;

public interface ValidatorService {

    boolean validateResponses(ResponseBody casperApiResponse, ResponseBody validatorPayLoad);

}
