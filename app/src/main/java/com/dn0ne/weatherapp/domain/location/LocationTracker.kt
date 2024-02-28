package com.dn0ne.weatherapp.domain.location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}