package com.sysco.sampleService.Stas.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HomeEnvelopeModel<T> {

    private List<T> data;
    private List<HomeError> errors;

    // HomeEnvelopeModel<HomeModelOnCalories>

    public static <T>HomeEnvelopeModel<T> generateResponse(List<T> data, List<HomeError> errors){
        return HomeEnvelopeModel.<T>builder()
                .data(data)
                .errors(errors)
                .build();
    }
}
