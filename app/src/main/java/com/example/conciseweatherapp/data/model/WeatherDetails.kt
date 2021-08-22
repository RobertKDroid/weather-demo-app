package com.example.conciseweatherapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDetails(
    val icon: String,
    val description: String
) : Parcelable
