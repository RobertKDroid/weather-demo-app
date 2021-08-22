package com.example.conciseweatherapp.presentation.fragment.weatherDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conciseweatherapp.data.model.DailyWeather
import com.example.conciseweatherapp.domain.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor() : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<WeatherDetailsState>()

    val stateLiveData = stateMutableLiveData as LiveData<WeatherDetailsState>

    fun processArgs(args: WeatherDetailsFragmentArgs) {
        stateMutableLiveData.postValue(
            WeatherDetailsState.WeatherDetails(
                args.weatherData,
                args.dailyWeather
            )
        )
    }

}

sealed class WeatherDetailsState {
    data class WeatherDetails(val weatherData: WeatherData, val dailyWeather: DailyWeather) :
        WeatherDetailsState()
}
