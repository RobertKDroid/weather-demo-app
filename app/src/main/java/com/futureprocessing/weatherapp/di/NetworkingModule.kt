package com.futureprocessing.weatherapp.di

import com.futureprocessing.weatherapp.networking.RetrofitBuilder
import com.futureprocessing.weatherapp.networking.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofit() = RetrofitBuilder.build()

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit) =
        retrofit.create(WeatherApiService::class.java)
}
