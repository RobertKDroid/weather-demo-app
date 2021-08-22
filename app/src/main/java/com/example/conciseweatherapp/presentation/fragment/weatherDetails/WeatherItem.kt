package com.example.conciseweatherapp.presentation.fragment.weatherDetails

import android.view.View
import com.example.conciseweatherapp.data.model.DailyWeather
import com.example.conciseweatherapp.utils.toCelsius
import com.example.conciseweatherapp.utils.toDateTimeFromSeconds
import com.example.conciseweatherapp.utils.toSimpleDateString
import com.futureprocessing.weatherapp.R
import com.futureprocessing.weatherapp.databinding.WeatherItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class WeatherItem constructor(
    private val dailyWeather: DailyWeather,
    private val onClick: (DailyWeather) -> Unit
) : BindableItem<WeatherItemBinding>() {

    override fun bind(viewBinding: WeatherItemBinding, position: Int) {
        val context = viewBinding.root.context
        viewBinding.nameTextView.text = context.getString(
            R.string.date_min_max_day_values,
            dailyWeather.date.toDateTimeFromSeconds().toSimpleDateString(),
            dailyWeather.temperatures.min.toCelsius(),
            dailyWeather.temperatures.max.toCelsius(),
            dailyWeather.temperatures.day.toCelsius()
        )
        viewBinding.root.setOnClickListener { onClick(dailyWeather) }
    }

    override fun getLayout(): Int = R.layout.weather_item

    override fun initializeViewBinding(view: View): WeatherItemBinding {
        return WeatherItemBinding.bind(view)
    }
}
