package com.dn0ne.weatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.core.location.LocationRequestCompat
import com.dn0ne.weatherapp.domain.location.Location
import com.dn0ne.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.getCurrentLocation(
                LocationRequestCompat.QUALITY_HIGH_ACCURACY,
                object : CancellationToken() {
                    override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken
                        = CancellationTokenSource().token

                    override fun isCancellationRequested() = false

                }
            ).apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(
                            Location(
                                latitude = result.latitude,
                                longitude = result.longitude
                            )
                        )

                    } else {
                        cont.resume(null)
                    }

                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    cont.resume(
                        Location(
                            latitude = result.latitude,
                            longitude = result.longitude
                        )
                    )
                }

                addOnFailureListener {
                    cont.resume(null)
                }

                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}