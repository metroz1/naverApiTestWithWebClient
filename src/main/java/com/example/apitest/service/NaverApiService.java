package com.example.apitest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class NaverApiService {

    private final WebClient webClient;

    public Mono<String> translateText(String text, String sourceLang, String targetLang) {

        String apiUrl = "/papago/n2mt";

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("source", sourceLang)
                .queryParam("target", targetLang)
                .queryParam("text", text);

        return webClient.post()
                .uri(uriComponentsBuilder.build().toString())
                .body(BodyInserters.empty())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> searchBlog(String text) {

        String apiUrl = "/search/blog.json?query=" + text;

        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> detectLanguage(String text) {

        String apiUrl = "/papago/detectLangs?query=" + text;

        return webClient.post()
                .uri(apiUrl)
                .body(BodyInserters.empty())
                .retrieve()
                .bodyToMono(String.class);
    }

    private String encodeText(String text) {

        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

}