package com.example.januarytrainingproject


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListExample() {
    val listViewModel: ListViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(100.dp))
        // LazyColumn to display the list
        LazyColumn(
            modifier = Modifier.height(500.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between items
        ) {
            items(listViewModel.items) { item ->
                ListItem(item)
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        // Button to add items to the list
        Button(onClick = { listViewModel.addItem() }) {
            Text(text = "Add Item")
        }
    }
}

@Composable
fun ListItem(item: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // Add horizontal padding
            .clip(RoundedCornerShape(8.dp)) // Rounded corners
            .background(Color.LightGray)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Simple border
            .padding(16.dp) // Padding inside the box
    ) {
        Text(text = item)
    }
}

class ListViewModel : ViewModel() {
    // Use a state-backed list to make it observable
    var items by mutableStateOf(listOf("Item 1", "Item 2", "Item 3"))
        private set

    private var currentIndex = 3



    fun addItem() {
        // Update the list by creating a new instance (required for recomposition)
        items = items + "Item ${++currentIndex}"
    }
}

