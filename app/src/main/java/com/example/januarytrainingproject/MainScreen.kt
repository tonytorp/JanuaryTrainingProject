package com.example.januarytrainingproject

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// Projekti löytyy gitistä
// https://github.com/tonytorp/JanuaryTrainingProject


@Composable
fun MainScreen(navController: NavHostController, counterViewModel: CounterViewModel ){
    var name by remember { mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Header( "Oma sovellus ${counterViewModel.count}")
        TextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = { Text("Enter your name") },
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            // Luetaan counterin arvo viewModelista (aiemmin rememberSaveable
            text = "Counter: ${counterViewModel.count}",
            fontSize = 40.sp,
        )

        Spacer(modifier = Modifier.height(100.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = {
                // Laskurin päivitys tapahtuu nyt viewModelin julkisten metodien
                // increment ja decrement kautta
                counterViewModel.increment()
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(32.dp) ,
                    tint = Color.Red
                )
                Text(stringResource(R.string.increase))
            }
            Spacer( modifier = Modifier.width(40.dp))
            Button(onClick={ counterViewModel.decrement() }) {
                Text(stringResource(R.string.decrease))
            }
        }
        Image(
            painter = painterResource(id = R.drawable.my_car),
            contentDescription = "My Car",
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )
        // Lisätään button, jolla voi navigoida detail -näyttöön
        Button(onClick = { navController.navigate("detail") }) {
            Text(text = "Go to Details")
        }


    }
}

