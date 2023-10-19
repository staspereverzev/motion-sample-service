package com.sysco.sampleService.Stas.service;

import com.sysco.sampleService.Stas.dao.HomeDao;
import com.sysco.sampleService.Stas.model.HomeEnvelopeModel;
import com.sysco.sampleService.Stas.model.HomeModelOnCalories;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeServiceTest {

    private HomeDao homeDao = mock(HomeDao.class);
    private HomeService homeService = new HomeService(homeDao);

    @Test
    public void test_service_success(){

        HomeModelOnCalories homeModelOnCalories = new HomeModelOnCalories("100", "Peanut Butter");
        when(homeDao.getProductOnCalories("9")).thenReturn(List.of(homeModelOnCalories));

        HomeEnvelopeModel<HomeModelOnCalories> response = homeService.getInfoOnCalories("9");

        assertNotNull(response);

        assertEquals(response.getData().get(0).getProductId(), "100");
        assertEquals(response.getData().get(0).getProductName(), "Peanut Butter");

    }

    @Test
    public void test_service_fail(){

        List<HomeModelOnCalories> list = new ArrayList<>();

        when(homeDao.getProductOnCalories("4")).thenReturn(List.of());
        HomeEnvelopeModel<HomeModelOnCalories> response = homeService.getInfoOnCalories("4");

        assertNotNull(response);
        assertTrue(response.getData().isEmpty());
        assertEquals(404, response.getErrors().get(0).getCode());
        assertEquals("No product with calories <4> ", response.getErrors().get(0).getMassage());
    }
}
