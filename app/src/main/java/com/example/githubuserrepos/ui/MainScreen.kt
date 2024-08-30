package com.example.githubuserrepos.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    var userId by remember { mutableStateOf(TextFieldValue()) }
    var searchPerformed by remember { mutableStateOf(false) }
    var showRepositories by remember { mutableStateOf(false) }

    val user by viewModel.user.collectAsState()
    val repos by viewModel.repos.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val totalForks by viewModel.totalForks.collectAsState(initial = 0)

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text("Enter a GitHub user id") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Red,
                        focusedLabelColor = Color.Red,
                        unfocusedLabelColor = Color.Gray
                    )
                )

                Button(
                    onClick = {
                        viewModel.fetchUserData(userId.text)
                        searchPerformed = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("SEARCH")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = searchPerformed && user != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(data = user?.avatar_url),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = user?.name ?: "",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 8.dp)
                    )

                    LaunchedEffect(key1 = searchPerformed) {
                        delay(500)
                        showRepositories = true
                    }
                }
            }

            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            AnimatedVisibility(
                visible = showRepositories && repos.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(repos) { repo ->
                        RepositoryItem(repo = repo) {
                            navController.navigate(
                                "detail_screen/${repo.name}/${repo.description ?: "No description"}/${repo.forks}/$totalForks"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RepositoryItem(repo: Repository, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
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