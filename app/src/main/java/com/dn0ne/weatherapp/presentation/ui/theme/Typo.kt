package com.dn0ne.weatherapp.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.dn0ne.weatherapp.R

val fonts = FontFamily(
    Font(resId = R.font.lato_black, weight = FontWeight.Black),
    Font(resId = R.font.lato_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(resId = R.font.lato_bold, weight = FontWeight.Bold),
    Font(resId = R.font.lato_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.lato_italic, style = FontStyle.Italic),
    Font(resId = R.font.lato_light, weight = FontWeight.Light),
    Font(resId = R.font.lato_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.lato_regular),
    Font(resId = R.font.lato_thin, weight = FontWeight.Thin),
    Font(resId = R.font.lato_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

val Typography = Typography().let {
    it.copy(
        displayLarge = it.displayLarge.copy(
            fontFamily = fonts
        ),
        displayMedium = it.displayMedium.copy(
            fontFamily = fonts
        ),
        displaySmall = it.displaySmall.copy(
            fontFamily = fonts
        ),
        headlineLarge = it.headlineLarge.copy(
            fontFamily = fonts
        ),
        headlineMedium = it.headlineMedium.copy(
            fontFamily = fonts
        ),
        headlineSmall = it.headlineSmall.copy(
            fontFamily = fonts
        ),
        titleLarge = it.titleLarge.copy(
            fontFamily = fonts
        ),
        titleMedium = it.titleMedium.copy(
            fontFamily = fonts
        ),
        titleSmall = it.titleSmall.copy(
            fontFamily = fonts
        ),
        bodyLarge = it.bodyLarge.copy(
            fontFamily = fonts
        ),
        bodyMedium = it.bodyMedium.copy(
            fontFamily = fonts
        ),
        bodySmall = it.bodySmall.copy(
            fontFamily = fonts
        ),
        labelLarge = it.labelLarge.copy(
            fontFamily = fonts
        ),
        labelMedium = it.labelMedium.copy(
            fontFamily = fonts
        ),
        labelSmall = it.labelSmall.copy(
            fontFamily = fonts
        )
    )
}