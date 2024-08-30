package com.example.githubuserrepos.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.githubuserrepos.R
import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    // State to hold the GitHub user ID input by the user
    var userId by remember { mutableStateOf(TextFieldValue()) }

    // Observe the user data and repository list from the ViewModel
    val user by viewModel.user.collectAsState()
    val repos by viewModel.repos.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Take Home") },
                backgroundColor = Color(0xFF3F51B5),
                contentColor = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // TextField for entering the GitHub user ID
            TextField(
                value = userId,
                onValueChange = { userId = it },
                label = { Text("Enter a github user id") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Button to initiate the search based on the entered user ID
            Button(
                onClick = { viewModel.fetchUserData(userId.text) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("SEARCH")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display the user's avatar and name if available
            user?.let {
                Image(
                    painter = rememberImagePainter(data = it.avatar_url),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
            }

            // Display an error message if there is one
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // LazyColumn to display the list of repositories
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(repos) { repo ->
                    RepositoryItem(repo = repo)
                }
            }
        }
    }
}

@Composable
fun RepositoryItem(repo: Repository) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = repo.name, style = MaterialTheme.typography.h6)
            Text(
                text = repo.description ?: "No description",
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}