package com.example.januarytrainingproject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import androidx.lifecycle.viewmodel.compose.viewModel

// Data class to represent a Todo item
data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

// Retrofit service interface for fetching Todos
interface TodoService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}

// ViewModel to manage fetching and storing todos
class TodoViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TodoService::class.java)

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedTodos = service.getTodos()
                _todos.value = fetchedTodos
            } catch (e: Exception) {
                _todos.value = emptyList() // Set to empty list in case of an error
            }
        }
    }
}

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    // Collect the StateFlow from the ViewModel
    val todos by viewModel.todos.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Display a LazyColumn of Todos
        if (todos.isNotEmpty()) {
            TodoList(todos = todos)
        } else {
            Text(text = "Loading Todos...", modifier = Modifier.padding(16.dp)) // Display a loading message
        }
    }
}

@Composable
fun TodoList(todos: List<Todo>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(todos.size) { index ->
            val todo = todos[index]
            TodoItem(todo = todo)
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "ID: ${todo.id}")
        Text(text = "Title: ${todo.title}")
        Text(text = "Completed: ${if (todo.completed) "Yes" else "No"}")
    }
}
