package com.dn0ne.weatherapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        typography = Typography
    )
}