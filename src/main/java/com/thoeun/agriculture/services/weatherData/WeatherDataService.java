package com.thoeun.agriculture.services.weatherData;

import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.models.WeatherData;
import com.thoeun.agriculture.repository.WeatherDataRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeatherDataService implements IWeatherDataService{

    private final WeatherDataRepository weatherDataRepository;
    private final IFarmService farmService;

    @Override
    public WeatherData createWeatherData(WeatherData weatherData) {
        Farm farm = farmService.getFarmById(weatherData.getFarm().getFarmId());
        if (farm == null) {
            throw  new ResourceNotFoundException("Farm Not Found!");
        }
//        if (weatherDataRepository.existsByTemperature(weatherData.getTemperature())) {
//            throw new AlreadyExistsException("WeatherData with the same Temperature already exists in this WeatherData!");
//        }
        return weatherDataRepository.save(weatherData);
    }

    @Override
    public WeatherData getWeatherDataById(Long weatherDataId) {
        return weatherDataRepository.findById(weatherDataId)
                .orElseThrow(() -> new ResourceNotFoundException("WeatherData not found with ID: " + weatherDataId));
    }

    @Override
    public List<WeatherData> getAllWeatherData() {
        try {
            return weatherDataRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public WeatherData updateWeatherData(Long weatherDataId, WeatherData weatherData) {
        WeatherData updateWeatherData = weatherDataRepository.findById(weatherDataId).orElseThrow(() -> new ResourceNotFoundException("WeatherData not found!"));
        updateWeatherData.setTemperature(weatherData.getTemperature());
        updateWeatherData.setHumidity(weatherData.getHumidity());
        updateWeatherData.setRainfall(weatherData.getRainfall());
        updateWeatherData.setWindSpeed(weatherData.getWindSpeed());
        updateWeatherData.setWeatherDate(weatherData.getWeatherDate());
        return weatherDataRepository.save(updateWeatherData);
    }

    @Override
    public void deleteWeatherData(Long weatherDataId) {
        weatherDataRepository.findById(weatherDataId).ifPresentOrElse(weatherDataRepository::delete, () -> {
            throw new ResourceNotFoundException("WeatherData Not Found!");
        });
    }
}
