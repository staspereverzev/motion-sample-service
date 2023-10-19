package com.sysco.sampleService.Stas.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HomeError {

    private int code;
    private String massage;

    public HomeError(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
