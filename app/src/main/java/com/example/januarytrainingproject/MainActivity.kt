package com.example.januarytrainingproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Tässä määritellään, mitä näytöllä näkyy (Material UI Composablet)
           MainScreen();
        }
    }
    override fun onStart(){
        super.onStart();
        Log.d("MY_ACTIVITY", "onStart");
    }
    override fun onStop() {
        super.onStop();
        Log.d("MY_ACTIVITY", "onStop");
    }
    override fun onDestroy() {
        super.onDestroy();
        Log.d("MY_ACTIVITY", "onDestroy");
    }
    // Lisäksi on funktiot onPause ja onResume, jotka voidaan ylikirjoittaa
}



















