package com.sysco.sampleService.Stas.model;

import lombok.Getter;

@Getter
public class HomeBaseModel {
    private String productId;
    private String productName;

    public HomeBaseModel(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
}
