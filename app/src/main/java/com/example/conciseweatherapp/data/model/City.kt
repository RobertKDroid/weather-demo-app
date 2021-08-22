package com.example.conciseweatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val lat: Float,
    val lon: Float,
    val name: String,
    val snow: Float = 0F
) : Parcelable
