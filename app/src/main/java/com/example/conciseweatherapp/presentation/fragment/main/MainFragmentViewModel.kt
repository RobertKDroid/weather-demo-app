package com.example.conciseweatherapp.presentation.fragment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conciseweatherapp.data.model.DailyWeather
import com.example.conciseweatherapp.domain.factory.WeatherItemsFactory
import com.example.conciseweatherapp.domain.model.WeatherData
import com.example.conciseweatherapp.domain.useCase.GetWeatherDataUseCase
import com.example.conciseweatherapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val weatherItemsFactory: WeatherItemsFactory
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val stateMutableLiveData = MutableLiveData<MainFragmentState>()

    val stateLiveData = stateMutableLiveData as LiveData<MainFragmentState>
    val singleLiveEvent = SingleLiveEvent<MainFragmentEvent>()

    init {
        getWeatherData()
    }

    fun getWeatherData() = disposables.add(
        getWeatherDataUseCase.getWeatherData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                stateMutableLiveData.postValue(MainFragmentState.WeatherFetched(it))
            }, {
                Timber.e(it)
                singleLiveEvent.postValue(MainFragmentEvent.WeatherFetchError(it.message))
            })
    )

    fun getWeatherListItems(list: List<DailyWeather>, onClick: (DailyWeather) -> Unit) =
        weatherItemsFactory.createDailyWeatherItems(list, onClick)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}

sealed class MainFragmentState {
    data class WeatherFetched(val weatherResponse: WeatherData) : MainFragmentState()
}

sealed class MainFragmentEvent {
    data class WeatherFetchError(val message: String?) : MainFragmentEvent()
}
