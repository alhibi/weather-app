package com.dn0ne.weatherapp.presentation.components.weathercard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Water
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dn0ne.weatherapp.domain.extensions.getTimeAsString
import com.dn0ne.weatherapp.domain.extensions.now
import com.dn0ne.weatherapp.presentation.WeatherState
import kotlinx.datetime.LocalDateTime
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    contentColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier,
    graphColor: Color = contentColor
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        val adaptiveContentColor by remember {
            mutableStateOf(contentColor)
        }

        val adaptiveGraphColor by remember {
            mutableStateOf(graphColor)
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = adaptiveContentColor
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                /*WeatherBackground(
                    weatherData = data,
                    derivedContentColor = {
                        adaptiveContentColor = it
                    },
                    derivedGraphColor = {
                        adaptiveGraphColor = it
                    },
                    modifier = Modifier.matchParentSize()
                )*/

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    val currentTime by remember {
                        derivedStateOf {
                            LocalDateTime.now().getTimeAsString()
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Place,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "At your location",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        Text(
                            text = "Today at $currentTime",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    val temperatureSize = 100
                    val localDensity = LocalDensity.current
                    Box(
                        modifier = Modifier
                            .height(
                                with(localDensity) {
                                    (temperatureSize + 10).sp.toDp()
                                }
                            )
                            .width(
                                with(localDensity) {
                                    val length = "${data.temperatureCelsius.roundToInt()}".length
                                    (temperatureSize * length * 2 + 50).toDp()
                                }
                            )
                    ) {
                        Text(
                            text = data.temperatureCelsius.roundToInt().toString(),
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = temperatureSize.sp
                            ),
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                        Text(
                            text = "°",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            fontSize = (temperatureSize / 3).sp,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp)
                                .align(Alignment.TopEnd)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = data.weatherType.weatherDesc,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        WeatherDataDisplay(
                            value = data.pressure.roundToInt(),
                            unit = "hpa",
                            icon = Icons.Outlined.Water,
                            iconTint = adaptiveContentColor,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        WeatherDataDisplay(
                            value = data.humidity.roundToInt(),
                            unit = "%",
                            icon = Icons.Outlined.WaterDrop,
                            iconTint = adaptiveContentColor,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        WeatherDataDisplay(
                            value = data.windSpeed.roundToInt(),
                            unit = "m/s",
                            icon = Icons.Outlined.Air,
                            iconTint = adaptiveContentColor,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    state.weatherInfo.weatherDataPerDay[0].let { dayData ->
                        dayData?.let {
                            Spacer(modifier = Modifier.height(16.dp))
                            TemperatureGraphDisplay(
                                temperatures = listOf(
                                    dayData[5].temperatureCelsius.toFloat(),
                                    dayData[11].temperatureCelsius.toFloat(),
                                    dayData[17].temperatureCelsius.toFloat(),
                                    dayData[22].temperatureCelsius.toFloat(),
                                ),
                                contentColor = adaptiveContentColor,
                                graphColor = adaptiveGraphColor,
                                graphPointsOuterColor = adaptiveGraphColor,
                                graphPointsInnerColor = Color.White,
                                textStyle = MaterialTheme.typography.bodyMedium,
                                temperatureTextStyle = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                        }
                    }


                }
            }
        }
    }
}