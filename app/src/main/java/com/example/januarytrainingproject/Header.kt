package com.example.januarytrainingproject

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Header -komponentti, joka näyttää tekstin ruudulla
// Todo: Header vie koko ruudun leveyden ja on 15% näytön korkeudesta
// Headerilla on taustaväri ja teksti, jonka se saa parametrina
@Composable
fun Header( title: String ){
    Text(
        text = title,
        fontSize = 50.sp, // Bigger font size,
        modifier = Modifier.fillMaxWidth()
            .height(100.dp)
            .background(Color.Yellow)
            .border( 3.dp, Color.Black, RoundedCornerShape(20.dp))
            .wrapContentSize(Alignment.Center)
    )
}


