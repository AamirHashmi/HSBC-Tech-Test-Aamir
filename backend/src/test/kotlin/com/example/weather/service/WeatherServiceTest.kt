package com.example.weather.service

import com.example.weather.model.City
import com.example.weather.model.Clouds
import com.example.weather.model.Coordinates
import com.example.weather.model.MainWeather
import com.example.weather.model.Rain
import com.example.weather.model.Weather
import com.example.weather.model.WeatherResponse
import com.example.weather.model.Wind
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.client.RestTemplate

@ExtendWith(MockitoExtension::class)
class WeatherServiceTest {

    @Mock
    private lateinit var restTemplate: RestTemplate

    private lateinit var weatherService: WeatherService

    @BeforeEach
    fun setup() {
        weatherService = WeatherService(restTemplate)
    }

    @Test
    fun `should return correct count of cities starting with a letter`() {
        // Given
        val url = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22"
        val mockResponse = createMockResponse()
        
        `when`(restTemplate.getForObject(url, WeatherResponse::class.java)).thenReturn(mockResponse)

        // When
        val result = weatherService.getCitiesByLetter("y")

        // Then
        assertEquals(1, result)
    }

    @Test
    fun `should be case insensitive`() {
        // Given
        val url = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22"
        val mockResponse = createMockResponse()
        
        `when`(restTemplate.getForObject(url, WeatherResponse::class.java)).thenReturn(mockResponse)

        // When
        val result = weatherService.getCitiesByLetter("Z")

        // Then
        assertEquals(3, result)
    }

    private fun createMockResponse(): WeatherResponse {
        val cities = listOf(
            createCity("Yafran"),
            createCity("Zuwarah"),
            createCity("Zawiya"),
            createCity("Zlitan")
        )

        return WeatherResponse(
            cod = "200",
            calctime = 0.3107,
            cnt = 4,
            name = "Test data",
            list = cities
        )
    }

    private fun createCity(name: String): City {
        return City(
            id = 123456,
            name = name,
            coord = Coordinates(lon = 12.5, lat = 32.0),
            main = MainWeather(
                temp = 9.68,
                temp_min = 9.681,
                temp_max = 9.681,
                pressure = 961.02,
                sea_level = 1036.82,
                grnd_level = 961.02,
                humidity = 85
            ),
            dt = 1485784982,
            wind = Wind(speed = 3.96, deg = 356.5),
            rain = Rain(`3h` = 0.255),
            clouds = Clouds(all = 88),
            weather = listOf(
                Weather(
                    id = 500,
                    main = "Rain",
                    description = "light rain",
                    icon = "10d"
                )
            )
        )
    }
}