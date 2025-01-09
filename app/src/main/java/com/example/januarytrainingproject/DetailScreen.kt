package com.example.januarytrainingproject

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


// Screen, joka vain näyttää counterin arvon, joka luetaan yhteisestä ViewModelista (todo)
// Valinnainen: Lisätään myös button Back, jolla voi navigoida takaisin
// Tänne navigoidaan MainScreeniltä
@Composable
fun DetailScreen(navController: NavHostController, counterViewModel: CounterViewModel) {
    Column {
        Text("Counter value is: ${counterViewModel.count}")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

