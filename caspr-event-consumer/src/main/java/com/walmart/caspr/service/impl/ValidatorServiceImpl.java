package com.walmart.caspr.service.impl;

import com.walmart.caspr.model.api.ResponseBody;
import com.walmart.caspr.service.ValidatorService;
import org.springframework.stereotype.Service;


@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public boolean validateResponses(ResponseBody casperApiResponse, ResponseBody validatorPayLoad) {
        return casperApiResponse.equals(validatorPayLoad);
    }
}
