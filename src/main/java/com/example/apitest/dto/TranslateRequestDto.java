package com.example.apitest.dto;

import lombok.Getter;

@Getter
public class TranslateRequestDto {

    private String text;
    private String source;
    private String target;
}
