package com.dn0ne.weatherapp.presentation.components.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dn0ne.weatherapp.domain.extensions.getTimeAsString
import com.dn0ne.weatherapp.domain.weather.WeatherData
import kotlin.math.roundToInt

@Composable
fun ForecastDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = weatherData.time.getTimeAsString(),
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor.copy(alpha = .5f)
        )
        Image(
            painter = painterResource(
                if (!weatherData.isDay && weatherData.weatherType.nightIconRes != -1) {
                    weatherData.weatherType.nightIconRes
                } else weatherData.weatherType.iconRes
            ),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius.roundToInt()}°",
            style = MaterialTheme.typography.titleMedium
                .copy(fontWeight = FontWeight.Bold, fontSize = 18.sp),
            color = contentColor
        )
    }
}