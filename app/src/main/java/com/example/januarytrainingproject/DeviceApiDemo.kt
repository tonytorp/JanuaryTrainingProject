package com.example.januarytrainingproject

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun DeviceApiDemo( sensorViewModel: SensorViewModel = viewModel(), locationViewModel: LocationViewModel = viewModel() ) {
    val sensorData = sensorViewModel.accelorometerValues.collectAsState()
    val locationViewModel: LocationViewModel = viewModel()
    val location by locationViewModel.locationFlow.collectAsState()

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text ("Sensor X: ${sensorData.value}", fontSize = 20.sp)

        Button(onClick = { sensorViewModel.listAllSensors() })
        {
            Text("List all sensors", fontSize = 20.sp)
        }
        Button(onClick = { sensorViewModel.startListeningAccelerometer() })
        {
            Text("Start Accelerometer", fontSize = 20.sp)
        }

        Text(
            text = location?.let {
                "GPS - LAT ${it.latitude} LNG ${it.longitude}"
            } ?: "GPS - Not available",
            fontSize = 20.sp
        )
        Button(onClick = { locationViewModel.getCurrentLocation() }) {
            Text("Get Current GPS", fontSize = 20.sp)
        }
        Button(onClick = { locationViewModel.startLocationUpdates() }) {
            Text("Start Listening GPS", fontSize = 20.sp)
        }
    }
}


// ViewModel, joka tarjoaa rajapinnan tämänhetkisen sijainnin hakemiseen sekä sijainnin kuuntelemiseen
class LocationViewModel(context: Application) : AndroidViewModel(context) {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val _locationFlow = MutableStateFlow<Location?>(null)
    val locationFlow: StateFlow<Location?> = _locationFlow.asStateFlow()
    val context = context

    // Get current location if permissions are granted
    fun getCurrentLocation() {
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            Log.d("LOCATION", location.toString())
            _locationFlow.value = location
        } else {
            Toast.makeText(context, "No permissions to access location", Toast.LENGTH_SHORT).show()
        }
    }

    // Start listening to location updates
    fun startLocationUpdates() {
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L, // Minimum time interval between updates in milliseconds
                1f, // Minimum distance between updates in meters
                // Tässä vaihtoehto kuuntelijaobjektin toteuttamiseen (vrt. Javan anonyymi luokka)
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        Log.d("LOCATION_UPDATE", location.toString())
                        _locationFlow.value = location
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

                    override fun onProviderEnabled(provider: String) {}

                    override fun onProviderDisabled(provider: String) {}
                }
            )
        } else {
            Toast.makeText(context, "No permissions to access location", Toast.LENGTH_SHORT).show()
        }
    }
}


// Toteutetaan ViewModel, joka kytkeytyy kuuntelemaan kiihtyvyysanturin lukemia
// Lukemat välitetään käyttöliittymälle ns. Flowna
class SensorViewModel( context: Application ) : AndroidViewModel( context ), SensorEventListener {
    val accelorometerValues = MutableStateFlow( 0f )

    val sensorManager = context.getSystemService( Context.SENSOR_SERVICE ) as SensorManager

    fun startListeningAccelerometer() {
        // Haetaan kiihtyvyysanturi ja rekisteröidytään kuuntelemaan eventtejä
        val accelerometer = sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER )
        if( accelerometer != null ){
            sensorManager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
    override fun onSensorChanged(p0: SensorEvent?) {
        if( p0 != null ){
            val x = p0.values[0]
            // Asetetaan uusi x: arvo Flow'hun
            accelorometerValues.value = x
        }
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    fun listAllSensors() {
        // Tutkitaan, mitä sensoreita laitteessa on. Otetaan yhteys SensorManageriin
        val sensors = sensorManager.getSensorList( Sensor.TYPE_ALL )
        for( sensor in sensors ){
            Toast.makeText( getApplication(), sensor.toString(), Toast.LENGTH_SHORT ).show()
        }
    }
}















