package com.sysco.sampleService.Stas.controller;

import com.sysco.sampleService.Kirill.model.ErrorResponse;
import com.sysco.sampleService.Kirill.model.ProductEnvelopeResponse;
import com.sysco.sampleService.Stas.model.HomeEnvelopeModel;

import com.sysco.sampleService.Stas.model.HomeError;
import com.sysco.sampleService.Stas.model.HomeModelOnCalories;
import com.sysco.sampleService.Stas.service.HomeService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class ControllerTest {

    private final HomeService homeService = mock(HomeService.class);

    HomeController homeController = new HomeController(homeService);



    @Test
    public void test_home_controller_success(){
        HomeModelOnCalories homeModelOnCalories = new HomeModelOnCalories("100", "Peanut Butter");
        List<HomeModelOnCalories> homeModelOnCaloriesList = List.of(homeModelOnCalories);

        when(homeService.getInfoOnCalories("4"))
                .thenReturn(HomeEnvelopeModel.generateResponse(homeModelOnCaloriesList, Collections.emptyList()));

        HomeEnvelopeModel<HomeModelOnCalories> response = homeController.getResponseOnCalories("4");

        assertNotNull(response);
        assertEquals("100", response.getData().get(0).getProductId());
        assertEquals("Peanut Butter", response.getData().get(0).getProductName());

        assertTrue(response.getErrors().isEmpty());
    }

    @Test
    public void test_home_controller_fail(){
        when(homeService.getInfoOnCalories("4"))
                .thenReturn(HomeEnvelopeModel.generateResponse(List.of(), List.of(new HomeError(404, "No product with"))));

        HomeEnvelopeModel<HomeModelOnCalories> response = homeController.getResponseOnCalories("4");

        assertNotNull(response.getErrors());
        assertTrue(response.getData().isEmpty());
        assertEquals(404, response.getErrors().get(0).getCode());
        assertEquals("No product with", response.getErrors().get(0).getMassage());

    }
}
