package com.example.weather.model

data class WeatherResponse(
    val cod: String,
    val calctime: Double,
    val cnt: Int,
    val name: String,
    val list: List<City>
)

data class City(
    val id: Long,
    val name: String,
    val coord: Coordinates,
    val main: MainWeather,
    val dt: Long,
    val wind: Wind,
    val rain: Rain?,
    val clouds: Clouds,
    val weather: List<Weather>
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)

data class MainWeather(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val sea_level: Double,
    val grnd_level: Double,
    val humidity: Int
)

data class Wind(
    val speed: Double,
    val deg: Double
)

data class Rain(
    val `3h`: Double?
)

data class Clouds(
    val all: Int
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
