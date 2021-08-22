package com.example.conciseweatherapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.futureprocessing.weatherapp.R
import org.joda.time.DateTime
import org.joda.time.Instant
import org.joda.time.format.DateTimeFormat
import kotlin.math.roundToInt

fun Float.toCelsius(): String =
    ((this - 32F) * 0.5556F).roundToInt().toString() + " °C"

fun Long.toDateTimeFromSeconds() = DateTime(Instant.ofEpochSecond(this))

fun DateTime.toSimpleDateString(pattern: String = "dd-MM-yyyy"): String {
    val formatter = DateTimeFormat.forPattern(pattern)
    return formatter.print(this.millis)
}

fun ImageView.loadWeatherIcon(icon: String) {
    Glide.with(this)
        .load("http://openweathermap.org/img/w/$icon.png")
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_placeholder_error)
        .into(this)
}
