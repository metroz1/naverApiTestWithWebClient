package com.example.apitest.controller;


import com.example.apitest.dto.TranslateRequestDto;
import com.example.apitest.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NaverApiController {

    private final NaverApiService naverApiService;

    @PostMapping("/translations")
    public Mono<String> translateText(@RequestBody TranslateRequestDto translateRequestDto) {

        System.out.println(translateRequestDto.getText());
        System.out.println(translateRequestDto.getSource());
        System.out.println(translateRequestDto.getTarget());

        return naverApiService.translateText(translateRequestDto.getText(), translateRequestDto.getSource(), translateRequestDto.getTarget());
    }

    @GetMapping("/search")
    public Mono<String> searchBlog(@RequestParam(name = "text") String text) {

        return naverApiService.searchBlog(text);
    }

    @PostMapping("/detections-languages")
    public Mono<String> detectLanguage(@RequestParam(name = "text") String text) {

        return naverApiService.detectLanguage(text);
    }
}
