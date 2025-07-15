package com.way.gtool.common.config;

import org.springframework.ai.model.ApiKey;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jingway
 * @date 2025/7/14 15:17
 * @description: 通过代理随机取apikey
 */
public record RotatingApiKey(String appKeys) implements ApiKey {

    @Override
    public String getValue() {
        var apiKeys = List.of(appKeys.split(","));
        return apiKeys.get(ThreadLocalRandom.current().nextInt(apiKeys.size()));
    }

    @Override
    public String toString() {
        return "RotatingApiKey{value='***'}";
    }

    public static RestClient buildRestClient(String baseUrl, ApiKey apiKey, MultiValueMap<String, String> headers,
         ResponseErrorHandler errorHandler) {
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultStatusHandler(errorHandler)
            .requestInterceptor((request, body, execution) -> {
                HttpHeaders httpHeaders = request.getHeaders();
                httpHeaders.setBearerAuth(apiKey.getValue()); // 每次调用动态 key
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                httpHeaders.addAll(headers);
                return execution.execute(request, body);
            })
            .build();
    }

    public static WebClient buildWebClient(String baseUrl, ApiKey apiKey, MultiValueMap<String, String> headers) {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeaders(h -> {
                h.setBearerAuth(apiKey.getValue()); // 每次调用动态 key
                h.setContentType(MediaType.APPLICATION_JSON);
                h.addAll(headers);
            })
            .build();
    }
}
