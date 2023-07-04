package com.example.apitest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Bean
    public WebClient webClient() {

        System.out.println(clientId + " " + clientSecret);

        return WebClient.builder()
                .baseUrl("https://openapi.naver.com/v1")
                .defaultHeader("X-Naver-Client-Id", clientId)
                .defaultHeader("X-Naver-Client-Secret", clientSecret)
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .build();
    }
}
