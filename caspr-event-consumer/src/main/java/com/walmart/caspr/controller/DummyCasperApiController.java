package com.walmart.caspr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.Logger;
import reactor.util.Loggers;

@RestController
public class DummyCasperApiController {

    private final Logger log = Loggers.getLogger(DummyCasperApiController.class);

    @PostMapping(value = "caps-app/capservices/fulfillment/accesspoints/v3/slots", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> postCasperApis(@RequestBody String message) {
        log.info("incoming message from request is : {}", message);
        return ResponseEntity.ok("{\"status\":\"OK\",\"payload\":{\"startDate\":\"2022-06-24\",\"endDate\":\"2022-05-30\",\"supportExpress\":false,\"supportedSlotTypes\":[\"100\"],\"slotDays\":[{\"slotDate\":\"_2022-05-24\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]},{\"slotDate\":\"_2022-05-25\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]},{\"slotDate\":\"_2022-05-26\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]},{\"slotDate\":\"_2022-05-27\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]},{\"slotDate\":\"_2022-05-28\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]},{\"slotDate\":\"_2022-05-29\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]},{\"slotDate\":\"_2022-05-30\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"supportedTimezone\":\"US/Eastern\",\"fulfillmentType\":\"INSTORE_PICKUP\",\"slots\":[]}],\"accessPointDetails\":[{\"accessPointId\":\"25bd10a1-46c5-46d3-b6ef-6aa2d8ea2d00\",\"accessPointName\":\"937 - Express Delivery\",\"supportedTimezone\":\"US/Eastern\",\"locationInstructions\":\"Follow orange 'Pickup' signs in Walmart parking lot. Park and wait in any 'Reserved Pickup Parking' spot\",\"minimumPurchase\":\"0.0\",\"storeId\":\"937\",\"fulfillmentStoreId\":\"937\",\"serviceAddress\":{\"isApoFpo\":\"N\",\"isPoBox\":\"N\",\"country\":\"US\",\"geoPoint\":{\"latitude\":\"34.018317\",\"longitude\":\"-84.557462\"},\"city\":\"MARIETTA\",\"postalCode\":\"300663361\",\"addressLineOne\":\"2795 CHASTAIN MEADOWS PKWY\",\"stateOrProvinceName\":\"GA\",\"state\":\"GA\"},\"isPilotTraining\":false,\"isTest\":false,\"isCrowdSourced\":true,\"acceptsEbtYN\":\"Y\",\"ebtPayInPerson\":\"N\",\"allowAgeRestricted\":false,\"allowAlcohol\":false}],\"serviceInfo\":{\"fulfillmentType\":\"INSTORE_PICKUP\",\"reservationType\":\"SLOTS\",\"dispenseType\":\"PICKUP_CURBSIDE\",\"priority\":0,\"carrier\":\"NA\",\"fulfillmentOption\":\"PICKUP\",\"locationType\":\"Store\",\"accessType\":\"PICKUP_CURBSIDE\"}}}");
    }

}
