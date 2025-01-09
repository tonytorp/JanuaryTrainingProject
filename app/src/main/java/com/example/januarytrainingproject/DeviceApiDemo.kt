package com.example.januarytrainingproject

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DeviceApiDemo( sensorViewModel: SensorViewModel = viewModel() ){
    val sensorData = sensorViewModel.accelorometerValues.collectAsState();

    Column {
        Text ("Sensor X: ${sensorData.value}", fontSize = 30.sp)

        Button(onClick = { sensorViewModel.listAllSensors() })
        {
            Text("List all sensors", fontSize = 40.sp)
        };
        Button(onClick = { sensorViewModel.startListeningAccelerometer() })
        {
            Text("Start Accelerometer", fontSize = 40.sp)
        };
    }
}
// Toteutetaan ViewModel, joka kytkeytyy kuuntelemaan kiihtyvyysanturin lukemia
// Lukemat välitetään käyttöliittymälle ns. Flowna
class SensorViewModel( context: Application ) : AndroidViewModel( context ), SensorEventListener {
    val accelorometerValues = MutableStateFlow( 0f );

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















