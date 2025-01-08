package com.example.januarytrainingproject

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MainScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Header( "Oma sovellus")
        Text(
            text = stringResource(R.string.hello_maailma),
            fontSize = 40.sp,
        )
        Spacer(modifier = Modifier.height(100.dp))
        Row(modifier = Modifier.padding(16.dp)) {
            Button(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(32.dp) ,
                    tint = Color.Red
                )
                Text(stringResource(R.string.increase))
            }
            Spacer( modifier = Modifier.width(40.dp))
            Button(onClick={}) {
                Text(stringResource(R.string.decrease))
            }
        }
        Image(
            painter = painterResource(id = R.drawable.my_car),
            contentDescription = "My Car",
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )
    }
}