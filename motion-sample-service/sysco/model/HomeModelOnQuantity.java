package com.sysco.sampleService.Stas.model;

public class HomeModelOnQuantity extends HomeBaseModel{
    private String quantity;

    public HomeModelOnQuantity(String productId, String productName, String quantity) {
        super(productId, productName);
        this.quantity = quantity;
    }
}
