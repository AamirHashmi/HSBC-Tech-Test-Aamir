package com.example.weather.controller

import com.example.weather.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cities")
class WeatherController(private val weatherService: WeatherService) {
    
    @GetMapping("/count")
    fun getCityCountByLetter(@RequestParam letter: String): Map<String, Int> {
        val count = weatherService.getCitiesByLetter(letter)
        return mapOf("count" to count)
    }
}
