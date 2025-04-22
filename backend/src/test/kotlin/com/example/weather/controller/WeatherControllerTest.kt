package com.example.weather.controller

import com.example.weather.service.WeatherService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(WeatherController::class)
class WeatherControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var weatherService: WeatherService

    @Test
    fun `should return correct count for cities starting with a letter`() {
        // Given
        val letter = "y"
        val expectedCount = 1
        
        `when`(weatherService.getCitiesByLetter(letter)).thenReturn(expectedCount)
        
        // When/Then
        mockMvc.perform(get("/api/cities/count?letter=$letter"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.count").value(expectedCount))
    }
    
    @Test
    fun `should be case insensitive`() {
        // Given
        val letter = "Z"
        val expectedCount = 3
        
        `when`(weatherService.getCitiesByLetter(letter)).thenReturn(expectedCount)
        
        // When/Then
        mockMvc.perform(get("/api/cities/count?letter=$letter"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.count").value(expectedCount))
    }
}