package com.dn0ne.weatherapp.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudOff
import com.dn0ne.weatherapp.presentation.components.refresh.PullRefreshIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dn0ne.weatherapp.domain.weather.WeatherType
import com.dn0ne.weatherapp.presentation.components.weathercard.WeatherCard
import com.dn0ne.weatherapp.presentation.components.forecast.WeatherForecast
import com.dn0ne.weatherapp.presentation.components.nextweather.NextDaysWeather
import com.dn0ne.weatherapp.presentation.components.refresh.pullRefresh
import com.dn0ne.weatherapp.presentation.components.refresh.rememberPullRefreshState
import com.dn0ne.weatherapp.presentation.components.weather.WeatherBackground

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    state: WeatherState,
    isLightBarsAppearanceEnabled: (Boolean) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) { paddingValues ->

        val pullRefreshState = rememberPullRefreshState(refreshing = state.isLoading, onRefresh = {
            viewModel.loadWeatherInfo()
        })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(state = pullRefreshState)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            AnimatedContent(
                targetState = state.isLoading,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "",
                modifier = Modifier.fillMaxSize()
            ) { isLoading ->
                if (!isLoading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        var adaptiveContentColor by remember {
                            mutableStateOf(Color.White)
                        }
                        var adaptiveGraphColor by remember {
                            mutableStateOf(Color.White)
                        }
                        val containerColor by remember {
                            mutableStateOf(Color.White.copy(alpha = .15f))
                        }

                        state.weatherInfo?.currentWeatherData?.let { data ->
                            WeatherBackground(
                                weatherData = data,
                                derivedContentColor = {
                                    adaptiveContentColor = it
                                },
                                derivedGraphColor = {
                                    adaptiveGraphColor = it
                                },
                                isLightBarsAppearanceEnabled = isLightBarsAppearanceEnabled,
                                modifier = Modifier.matchParentSize()
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize()
                        ) {
                            AnimatedVisibility(visible = state.error != null) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.CloudOff,
                                        contentDescription = null,
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Text(
                                        text = state.error ?: "",
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 48.dp, vertical = 8.dp)
                                    )
                                }
                            }

                            WeatherCard(
                                state = state,
                                contentColor = adaptiveContentColor,
                                containerColor = containerColor,
                                graphColor = adaptiveGraphColor
                            )

                            WeatherForecast(
                                state = state,
                                containerColor = containerColor,
                                contentColor = adaptiveContentColor,
                            )

                            NextDaysWeather(
                                state = state,
                                contentColor = adaptiveContentColor,
                                containerColor = containerColor,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize())
                }
            }

            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullRefreshState,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}