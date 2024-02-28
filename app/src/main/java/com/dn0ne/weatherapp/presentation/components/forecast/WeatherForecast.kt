package com.dn0ne.weatherapp.presentation.components.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dn0ne.weatherapp.domain.extensions.now
import com.dn0ne.weatherapp.presentation.WeatherState
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentColor: Color = LocalContentColor.current
) {
    state.weatherInfo?.weatherDataPerDay?.let { dataLists ->
        val todayWeatherSinceNow = dataLists[0]?.dropWhile {
            it.time.hour != LocalDateTime.now().hour
        } ?: emptyList()
        val tomorrowWeather = dataLists[1]?.dropLastWhile {
            it.time.hour != 23 - todayWeatherSinceNow.size
        } ?: emptyList()

        val dataList = todayWeatherSinceNow + tomorrowWeather

        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = containerColor)
        ) {
            /*Text(
                text = "Today",
                style = MaterialTheme.typography.titleMedium
                    .copy(fontSize = 20.sp),
                color = contentColor,
                modifier = Modifier.padding(horizontal = 12.dp)
            )*/

            val coroutineScope = rememberCoroutineScope()
            val listState = rememberLazyListState().apply {
                coroutineScope.launch {
                    scrollToItem(
                        dataList.indexOf(
                            dataList.find {
                                val currentHour = LocalDateTime.now().hour
                                it.time.hour == currentHour
                            }
                        )
                    )
                }
            }

            LazyRow(
                state = listState,
                modifier = Modifier
                    .drawWithContent {
                        drawContent()

                        val shadeRectWidth = 80f
                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    containerColor,
                                    Color.Transparent
                                ),
                                endX = shadeRectWidth
                            ),
                            topLeft = Offset.Zero,
                            size = Size(width = shadeRectWidth, height = size.height)
                        )

                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    containerColor
                                ),
                                startX = size.width - shadeRectWidth,
                                endX = size.width
                            ),
                            topLeft = Offset(x = size.width - shadeRectWidth, y = 0f),
                            size = Size(width = shadeRectWidth, height = size.height)
                        )
                    }
                    .padding(vertical = 16.dp)
            ) {
                items(items = dataList) { data ->
                    Spacer(modifier = Modifier.width(8.dp))
                    ForecastDisplay(weatherData = data, contentColor = contentColor)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}