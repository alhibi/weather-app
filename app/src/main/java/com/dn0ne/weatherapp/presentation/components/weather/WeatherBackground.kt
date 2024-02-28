package com.dn0ne.weatherapp.presentation.components.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dn0ne.weatherapp.domain.weather.WeatherData
import com.dn0ne.weatherapp.domain.weather.WeatherIndicatorLevel
import com.dn0ne.weatherapp.presentation.ui.theme.Blue
import com.dn0ne.weatherapp.presentation.ui.theme.EveningBrown
import com.dn0ne.weatherapp.presentation.ui.theme.EveningOrange
import com.dn0ne.weatherapp.presentation.ui.theme.NightBlue
import com.dn0ne.weatherapp.presentation.ui.theme.RainGray
import com.dn0ne.weatherapp.presentation.ui.theme.SkyBlue
import com.dn0ne.weatherapp.presentation.ui.theme.UltraLightBlue

@Composable
fun WeatherBackground(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    derivedContentColor: (Color) -> Unit = {},
    derivedGraphColor: (Color) -> Unit = {},
    isLightBarsAppearanceEnabled: (Boolean) -> Unit = {}
) {
    var backgroundColor by remember {
        mutableStateOf(
            when (weatherData.time.hour) {
                in 5..8 -> {
                    isLightBarsAppearanceEnabled(true)
                    derivedContentColor(Color.Black)
                    derivedGraphColor(EveningBrown)
                    EveningOrange
                }

                in 9..18 -> {
                    isLightBarsAppearanceEnabled(true)
                    derivedContentColor(Blue)
                    derivedGraphColor(Blue)
                    SkyBlue
                }

                in 19..21 -> {
                    isLightBarsAppearanceEnabled(true)
                    derivedContentColor(Color.Black)
                    derivedGraphColor(EveningBrown)
                    EveningOrange
                }

                else -> {
                    isLightBarsAppearanceEnabled(false)
                    derivedGraphColor(UltraLightBlue)
                    derivedContentColor(Color.White)
                    NightBlue
                }
            }
        )
    }

    Box(modifier = modifier.background(color = backgroundColor)) {
        weatherData.weatherType.run {
            if (
                !weatherData.isDay &&
                cloudiness !is WeatherIndicatorLevel.Medium &&
                raininess is WeatherIndicatorLevel.Zero &&
                snowiness is WeatherIndicatorLevel.Zero
            ) {
                Stars(
                    modifier = Modifier.matchParentSize(),
                    starColor = Color.White.copy(alpha = .3f),
                    maxStarSize = 5f
                )
            }

            when (cloudiness) {
                WeatherIndicatorLevel.Low -> {
                    Clouds(
                        modifier = Modifier.matchParentSize(),
                        alpha = .7f
                    )
                }

                WeatherIndicatorLevel.Medium -> {
                    RainClouds(
                        modifier = Modifier.matchParentSize(),
                        alpha = .5f
                    )

                    if (weatherData.isDay &&
                        (raininess !is WeatherIndicatorLevel.Zero ||
                        snowiness !is WeatherIndicatorLevel.Zero)
                    ) {
                        isLightBarsAppearanceEnabled(false)
                        backgroundColor = RainGray
                        derivedContentColor(Color.White)
                        derivedGraphColor(UltraLightBlue)

                    }
                }

                else -> Unit
            }

            when (raininess) {
                WeatherIndicatorLevel.Low -> {
                    Rain(
                        modifier = Modifier.matchParentSize(),
                        durationMillis = 15000,
                        dropColor = Color.White.copy(alpha = .2f)
                    )
                }

                WeatherIndicatorLevel.Medium -> {
                    Rain(
                        modifier = Modifier.matchParentSize(),
                        durationMillis = 10000,
                        dropsCount = 1000,
                        dropColor = Color.White.copy(alpha = .2f),
                        rotationAngle = 20f
                    )
                }

                WeatherIndicatorLevel.High -> {
                    Rain(
                        modifier = Modifier.matchParentSize(),
                        dropsCount = 1500,
                        dropColor = Color.White.copy(alpha = .2f),
                        rotationAngle = 30f
                    )
                }

                else -> Unit
            }

            when (snowiness) {
                WeatherIndicatorLevel.Low -> {
                    Snow(
                        modifier = Modifier.matchParentSize(),
                        snowColor = Color.White.copy(alpha = .5f)
                    )
                }

                WeatherIndicatorLevel.Medium -> {
                    Snow(
                        modifier = Modifier.matchParentSize(),
                        snowColor = Color.White.copy(alpha = .5f),
                        durationMillis = 30000,
                        shakeRadius = 20f
                    )
                }

                WeatherIndicatorLevel.High -> {
                    Snow(
                        modifier = Modifier.matchParentSize(),
                        snowColor = Color.White.copy(alpha = .5f),
                        durationMillis = 10000,
                        flakesCount = 1000,
                        shakeRadius = 30f,
                        rotationAngle = 20f
                    )
                }

                else -> Unit
            }
        }
    }
}