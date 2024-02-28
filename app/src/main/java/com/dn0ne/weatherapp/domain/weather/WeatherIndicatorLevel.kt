package com.dn0ne.weatherapp.domain.weather

sealed class WeatherIndicatorLevel {
    data object Zero: WeatherIndicatorLevel()
    data object Low: WeatherIndicatorLevel()
    data object Medium: WeatherIndicatorLevel()
    data object High: WeatherIndicatorLevel()
}