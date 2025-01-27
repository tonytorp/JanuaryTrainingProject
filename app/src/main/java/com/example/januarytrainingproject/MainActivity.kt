package com.example.januarytrainingproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.joinAll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sovelluksen käynnistyessä pyydetään lupa lokaation käyttöön (dialogi näkyy vain, jos lupa jo on)
        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
        setContent {
            DeviceApiDemo()
            //MyApp();
            //TodoScreen(); // RetrofitDemo
        }

    }
    override fun onStart(){
        super.onStart()
        Log.d("MY_ACTIVITY", "onStart")
    }
    override fun onStop() {
        super.onStop()
        Log.d("MY_ACTIVITY", "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MY_ACTIVITY", "onDestroy")
    }
    // Lisäksi on funktiot onPause ja onResume, jotka voidaan ylikirjoittaa
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val counterViewModel: CounterViewModel = viewModel()

    NavHost( navController = navController, startDestination = "home") {
        // Luodaan screenit, joille välitetään tällä tasolla luotu navController sekä viewModel
        composable("home"){
            MainScreen(navController, counterViewModel)
        }
        composable("detail"){
            DetailScreen(navController, counterViewModel)
        }
    }
}


class CounterViewModel : ViewModel() {
    var count by mutableIntStateOf(0)
        private set

    fun increment() {
        count++
    }
    fun decrement() {
        count--
    }
}
















