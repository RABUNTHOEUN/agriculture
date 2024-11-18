package com.thoeun.agriculture.services.weatherData;

import com.thoeun.agriculture.models.WeatherData;

import java.util.List;

public interface IWeatherDataService {
    WeatherData createWeatherData(WeatherData weatherData);
    WeatherData getWeatherDataById(Long weatherDataId);
    List<WeatherData> getAllWeatherData();
    WeatherData updateWeatherData(Long weatherDataId, WeatherData weatherData);
    void deleteWeatherData(Long weatherDataId);
}
