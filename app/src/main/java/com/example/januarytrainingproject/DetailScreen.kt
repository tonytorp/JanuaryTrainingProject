package com.example.januarytrainingproject

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


// Screen, joka vain näyttää counterin arvon, joka luetaan yhteisestä ViewModelista (todo)
// Valinnainen: Lisätään myös button Back, jolla voi navigoida takaisin
// Tänne navigoidaan MainScreeniltä
@Composable
fun DetailScreen(navController: NavHostController, counterViewModel: CounterViewModel) {
    val context = LocalContext.current
    Column {
        Text("Counter value is: ${counterViewModel.count}", fontSize = 30.sp)

        Button(onClick = {
            openWebPage(context, "https://www.google.com")
        }) {
            Text("Open Google", fontSize = 30.sp)
        }
        Button(onClick = {
            startTimer( context, "Time is up", 10)
            Toast.makeText( context, "Timer started", Toast.LENGTH_SHORT).show()
        }) {
            Text("Start 10 sec timer", fontSize = 30.sp)
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Back", fontSize = 30.sp)
        }
        ListExample()
    }
}
// Vaatii manifestiin: <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
fun startTimer( context: Context, message: String, seconds: Int ){
    val intent = Intent(AlarmClock.ACTION_SET_TIMER)
    intent.putExtra(AlarmClock.EXTRA_MESSAGE, message)
    intent.putExtra(AlarmClock.EXTRA_LENGTH, seconds)
    intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true)


    try {
        context.startActivity(intent)
    } catch (e: Exception) { // Voi tulla ActivityNotFoundException tai SecurityException
        e.printStackTrace()
    }
}

fun createAlarm( context: Context, message: String, hour: Int, minutes: Int ){
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
    }
}

fun openWebPage(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}


