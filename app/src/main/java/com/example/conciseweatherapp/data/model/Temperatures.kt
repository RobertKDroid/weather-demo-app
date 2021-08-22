package com.example.conciseweatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperatures(
    val day: Float,
    val eve: Float,
    val max: Float,
    val min: Float,
    val morn: Float,
    var night: Float
) : Parcelable
