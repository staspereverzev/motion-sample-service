package com.sysco.sampleService.Stas.controller;

import com.sysco.sampleService.Stas.model.HomeEnvelopeModel;
import com.sysco.sampleService.Stas.model.HomeModelOnCalories;
import com.sysco.sampleService.Stas.model.HomeModelOnQuantity;
import com.sysco.sampleService.Stas.service.HomeService;
import com.sysco.sampleService.Stas.validation.InputValidation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/products/{calories}/calories/home")
    public HomeEnvelopeModel<HomeModelOnCalories> getResponseOnCalories(@PathVariable @InputValidation String calories){
        return homeService.getInfoOnCalories(calories);
    }

    @GetMapping("/products/{quantity}/quantity/home")
    public HomeEnvelopeModel<HomeModelOnQuantity> getResponseOnQuantity(@PathVariable @InputValidation String quantity){
        return homeService.getProductOnQuantity(quantity);
    }
}
