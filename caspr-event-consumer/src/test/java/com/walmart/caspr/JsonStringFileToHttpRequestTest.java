package com.walmart.caspr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonStringFileToHttpRequestTest {
    @Test
    void testConvertJsonStringFileToHttpRequest() throws IOException {
        String customerDetailsJson = new String(Files.readAllBytes(new ClassPathResource("httpRequest.json").getFile().toPath()));

        HashMap<String, Object> hashMap = new ObjectMapper().readValue(customerDetailsJson, HashMap.class);
        assertNotNull(hashMap);
    }
}
