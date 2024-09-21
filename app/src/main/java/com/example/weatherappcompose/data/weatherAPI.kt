package com.example.weatherappcompose.data

import com.example.weatherappcompose.Constants.BASE_URL
import com.example.weatherappcompose.Models.TopModel
import com.example.weatherappcompose.Models.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface weatherAPI {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") language: String, // Added language as an optional query parameter
    ): TopModel



    companion object {

        fun createRetrofitInstance(): weatherAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(weatherAPI::class.java)
        }
    }
}