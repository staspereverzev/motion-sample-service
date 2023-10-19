package com.sysco.sampleService.Stas.exception;


import com.sysco.sampleService.Stas.model.HomeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HomeExceptionHandler {

    @ExceptionHandler(value = ConnectionFailedException.class)
    public ResponseEntity<HomeError> handleConnection(ConnectionFailedException ce){
        HomeError homeError = new HomeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ce.getMessage());
        return new ResponseEntity<>(homeError, HttpStatusCode.valueOf(homeError.getCode()));
    }

    @ExceptionHandler(value = RowMapperException.class)
    public ResponseEntity<HomeError> handleRowMapper(RowMapperException re){
        HomeError homeError = new HomeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), re.getMessage());
        return new ResponseEntity<>(homeError, HttpStatusCode.valueOf(homeError.getCode()));
    }
}
