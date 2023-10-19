package com.sysco.sampleService.Stas.service;

import com.sysco.sampleService.Stas.dao.HomeDao;
import com.sysco.sampleService.Stas.model.HomeEnvelopeModel;
import com.sysco.sampleService.Stas.model.HomeError;
import com.sysco.sampleService.Stas.model.HomeModelOnCalories;
import com.sysco.sampleService.Stas.model.HomeModelOnQuantity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class HomeService {

    private final HomeDao homeDao;

    public HomeService(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    public HomeEnvelopeModel<HomeModelOnCalories> getInfoOnCalories(String calories) {
        List<HomeModelOnCalories> homeModelOnCaloriesList = homeDao.getProductOnCalories(calories); // list.of()

        if (homeModelOnCaloriesList.isEmpty()) {
            HomeError homeError = new HomeError(HttpStatus.NOT_FOUND.value(), format("No product with calories <%s> ", calories));
            return HomeEnvelopeModel.generateResponse(homeModelOnCaloriesList, List.of(homeError));
        }

        return HomeEnvelopeModel.generateResponse(homeModelOnCaloriesList, List.of());
    }

    public HomeEnvelopeModel<HomeModelOnQuantity> getProductOnQuantity(String quantity){
        List<HomeModelOnQuantity> homeModelOnQuantityList = homeDao.getProductOnQuantity(quantity);

        if (homeModelOnQuantityList.isEmpty()) {
            HomeError homeError = new HomeError(HttpStatus.NOT_FOUND.value(), format("No product with calories <%s> ", quantity));
            return HomeEnvelopeModel.generateResponse(homeModelOnQuantityList, List.of(homeError));
        }

        return HomeEnvelopeModel.generateResponse(homeModelOnQuantityList, List.of());
    }
}
