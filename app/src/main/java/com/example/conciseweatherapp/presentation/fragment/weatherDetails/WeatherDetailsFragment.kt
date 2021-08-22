package com.example.conciseweatherapp.presentation.fragment.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.conciseweatherapp.utils.loadWeatherIcon
import com.example.conciseweatherapp.utils.toCelsius
import com.example.conciseweatherapp.utils.toDateTimeFromSeconds
import com.example.conciseweatherapp.utils.toSimpleDateString
import com.futureprocessing.weatherapp.R
import com.futureprocessing.weatherapp.databinding.FragmentWeatherDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailsBinding

    private val args: WeatherDetailsFragmentArgs by navArgs()
    private val viewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is WeatherDetailsState.WeatherDetails -> renderDetails(it)
            }
        })
        viewModel.processArgs(args)
    }

    private fun renderDetails(state: WeatherDetailsState.WeatherDetails) {
        val dailyWeather = state.dailyWeather
        val weatherData = state.weatherData

        with(binding) {
            cityNameTextView.text = weatherData.city.name
            dateTextView.text = dailyWeather.date.toDateTimeFromSeconds()
                .toSimpleDateString("EEEE',' dd-MM-yyyy")
            temperatureMinimumValueTextView.text =
                dailyWeather.temperatures.min.toCelsius()
            temperatureMaximumValueTextView.text =
                dailyWeather.temperatures.min.toCelsius()
            pressureValueTextView.text =
                getString(R.string.pressure_amount, dailyWeather.pressure.roundToInt())
            windValueTextView.text =
                getString(R.string.wind_speed, dailyWeather.windSpeed.roundToLong())
            dailyTemperatureWeatherDetailsTextView.text =
                dailyWeather.weatherDetails.first().description
            dailyTemperatureTextView.text = dailyWeather.temperatures.day.toCelsius()
            dailyTemperatureIconImageView.loadWeatherIcon(dailyWeather.weatherDetails.first().icon)
        }
    }

}
