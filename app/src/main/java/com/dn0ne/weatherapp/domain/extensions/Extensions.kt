package com.dn0ne.weatherapp.domain.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.getTimeAsString(): String {
    val hours = if (hour < 10) "0$hour" else "$hour"
    val minutes = if (minute < 10) "0$minute" else "$minute"
    return "$hours:$minutes"
}

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun String.capitalize(): String {
    return first().uppercase() + drop(1).lowercase()
}