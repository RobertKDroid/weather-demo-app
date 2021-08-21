package com.futureprocessing.weatherapp.ui.fragments

import com.futureprocessing.weatherapp.networking.WeatherApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) {


    fun

}

sealed class MainFragmentState

class GetWeatherDataUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    fun getWeatherData(observeOn: Scheduler = Schedulers.io()) =
        weatherRepository.getWeatherData().observeOn(observeOn)

}

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService
) {

    fun getWeatherData() = weatherApiService.getWeatherData()
        .subscribeOn(Schedulers.io())
}
