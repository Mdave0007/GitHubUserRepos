package com.example.githubuserrepos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.viewmodel.MainViewModel

// MainScreen composable function for the main UI screen
@Composable
fun MainScreen(viewModel: MainViewModel) {
    // State to hold the GitHub user ID input by the user
    var userId by remember { mutableStateOf(TextFieldValue()) }

    // Observe the user data and repository list from the ViewModel
    val user by viewModel.user.collectAsState()
    val repos by viewModel.repos.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // TextField for entering the GitHub user ID
        BasicTextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Button to initiate the search based on the entered user ID
        Button(
            onClick = { viewModel.fetchUserData(userId.text) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Search")
        }

        // Display the user's avatar and name if available
        user?.let {
            Image(
                painter = rememberImagePainter(data = it.avatar_url),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 16.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = it.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // LazyColumn to display the list of repositories
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(repos) { repo ->
                RepositoryItem(repo = repo) {
                    // TODO: Navigate to DetailScreen with selected repo
                }
            }
        }
    }
}

// RepositoryItem composable function to display individual repository information
@Composable
fun RepositoryItem(repo: Repository, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(text = repo.name, style = MaterialTheme.typography.h6)
        Text(
            text = repo.description ?: "No description",
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
    }
}