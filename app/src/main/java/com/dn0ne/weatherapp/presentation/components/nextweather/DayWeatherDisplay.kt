package com.dn0ne.weatherapp.presentation.components.nextweather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dn0ne.weatherapp.domain.extensions.capitalize
import com.dn0ne.weatherapp.domain.weather.WeatherData
import kotlin.math.roundToInt

@Composable
fun DayWeatherDisplay(
    weatherDataList: List<WeatherData>,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val dateTime = weatherDataList.first().time
        val weatherTypeDay = weatherDataList[11].weatherType
        val weatherTypeNight = weatherDataList[23].weatherType
        val maxTemperature = weatherDataList.maxOf { it.temperatureCelsius }.roundToInt()
        val minTemperature = weatherDataList.minOf { it.temperatureCelsius }.roundToInt()

        Column {
            Text(
                text = dateTime.dayOfWeek.name.capitalize(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "${dateTime.dayOfMonth} " + dateTime.month.name.capitalize(),
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor.copy(alpha = 0.5f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = weatherTypeDay.iconRes),
                    contentDescription = weatherTypeDay.weatherDesc,
                    modifier = Modifier.size(36.dp)
                )
                Image(
                    painter = painterResource(
                        id = if (weatherTypeNight.nightIconRes != -1) {
                            weatherTypeNight.nightIconRes
                        } else weatherTypeNight.iconRes
                    ),
                    contentDescription = weatherTypeNight.weatherDesc,
                    modifier = Modifier.size(36.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(60.dp)
            ) {
                Text(
                    text = "$maxTemperature°",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
                Text(
                    text = "$minTemperature°",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
            }
        }

    }
}