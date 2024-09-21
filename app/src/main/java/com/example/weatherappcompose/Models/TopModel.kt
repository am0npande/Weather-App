package com.example.weatherappcompose.Models

data class TopModel(

    val main: Main,
    val name: String,
    val weather: List<Weather>,
)