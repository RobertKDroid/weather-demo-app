package com.example.conciseweatherapp.domain.useCase

import com.example.conciseweatherapp.data.model.DailyWeather
import com.example.conciseweatherapp.domain.model.WeatherData
import com.example.conciseweatherapp.domain.repository.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    fun getWeatherData(observeOn: Scheduler = Schedulers.io()) =
        weatherRepository.getWeatherData().observeOn(observeOn)
            .map {
                WeatherData(
                    it.city,
                    it.dailyWeather,
                    getAverageTemperature(it.dailyWeather)
                )
            }

    private fun getAverageTemperature(list: List<DailyWeather>): Float {
        var averageTemperature = 0F
        list.forEach {
            averageTemperature += it.temperatures.day
        }
        return averageTemperature.div(list.size)
    }

}
