package com.dn0ne.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.dn0ne.weatherapp.R
import com.dn0ne.weatherapp.domain.extensions.now
import kotlinx.datetime.LocalDateTime

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val nightIconRes: Int = -1,
    val raininess: WeatherIndicatorLevel = WeatherIndicatorLevel.Zero,
    val snowiness: WeatherIndicatorLevel = WeatherIndicatorLevel.Zero,
    val cloudiness: WeatherIndicatorLevel = WeatherIndicatorLevel.Zero,
) {

    data object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.sunny,
        nightIconRes = R.drawable.moonlit
    )
    data object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.cloudy,
        cloudiness = WeatherIndicatorLevel.Low
    )
    data object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.cloudy,
        cloudiness = WeatherIndicatorLevel.Low
    )
    data object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.cloudy,
        cloudiness = WeatherIndicatorLevel.Medium
    )
    data object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.verycloudy,
        cloudiness = WeatherIndicatorLevel.Medium
    )
    data object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.verycloudy,
        cloudiness = WeatherIndicatorLevel.Medium
    )
    data object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Low
    )
    data object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Low
    )
    data object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Low
    )
    data object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Low,
        snowiness = WeatherIndicatorLevel.Low
    )
    data object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Low,
        snowiness = WeatherIndicatorLevel.Low
    )
    data object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium,
    )
    data object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium,
    )
    data object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium,
    )
    data object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Medium
    )
    data object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.snowy,
        cloudiness = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Low
    )
    data object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.snowy,
        cloudiness = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Medium
    )
    data object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.snowy,
        cloudiness = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Medium
    )
    data object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.snowy,
        cloudiness = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Medium
    )
    data object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium
    )
    data object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium
    )
    data object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.rainy,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.Medium
    )
    data object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.snowy,
        cloudiness = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Low
    )
    data object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.snowy,
        cloudiness = WeatherIndicatorLevel.Medium,
        snowiness = WeatherIndicatorLevel.Medium
    )
    data object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.rainythunder,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.High
    )
    data object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.rainythunder,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.High
    )
    data object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.rainythunder,
        cloudiness = WeatherIndicatorLevel.Medium,
        raininess = WeatherIndicatorLevel.High
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}