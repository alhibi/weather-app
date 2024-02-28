package com.dn0ne.weatherapp.presentation.components.nextweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dn0ne.weatherapp.presentation.WeatherState

@Composable
fun NextDaysWeather(
    state: WeatherState,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
    containerColor: Color = MaterialTheme.colorScheme.background
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = containerColor)
            .padding(16.dp)
    ) {
        state.weatherInfo?.let { weatherInfo ->

            /*Text(
                text = "Next days",
                style = MaterialTheme.typography.titleMedium
                    .copy(fontSize = 20.sp),
                color = contentColor,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )*/

            weatherInfo.weatherDataPerDay.forEach { (dayNumber, data) ->
                if (dayNumber > 0) {
                    DayWeatherDisplay(
                        weatherDataList = data,
                        contentColor = contentColor,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}