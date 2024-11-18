package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.WeatherData;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.weatherData.IWeatherDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/weatherData")
@AllArgsConstructor
public class WeatherDataController {

    private final IWeatherDataService weatherDataService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createWeatherData(@Valid @RequestBody WeatherData weatherData) {
        try {
            try {
                farmService.getFarmById(weatherData.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            WeatherData createdWeatherData = weatherDataService.createWeatherData(weatherData);
            return ResponseEntity.status(CREATED).body(new ApiResponse("WeatherData created successfully", createdWeatherData));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllLivestock () {
        try {
            List<WeatherData> weatherData = weatherDataService.getAllWeatherData();
            return ResponseEntity.ok(new ApiResponse("Success", weatherData));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{weatherDataId}")
    public ResponseEntity<ApiResponse> getWeatherDataById(@PathVariable Long weatherDataId) {
        try {
            WeatherData weatherData = weatherDataService.getWeatherDataById(weatherDataId);
            return ResponseEntity.ok(new ApiResponse("Found ", weatherData));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{weatherDataId}")
    public ResponseEntity<ApiResponse> updateWeatherData(@PathVariable Long weatherDataId,@Valid @RequestBody WeatherData weatherData) {

        try {
            WeatherData updatedWeatherData = weatherDataService.updateWeatherData(weatherDataId, weatherData);
            return ResponseEntity.ok(new ApiResponse("WeatherData update success", updatedWeatherData));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{weatherDataId}")
    public ResponseEntity<ApiResponse> deleteWeatherData(@PathVariable Long weatherDataId) {
        try {
            weatherDataService.deleteWeatherData(weatherDataId);
            return ResponseEntity.ok(new ApiResponse("WeatherData deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
