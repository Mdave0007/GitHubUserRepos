package com.example.githubuserrepos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.githubuserrepos.model.Repository

// DetailScreen composable function for displaying repository details
@Composable
fun DetailScreen(repo: Repository, totalForks: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = repo.name, style = MaterialTheme.typography.h4)
        Text(
            text = repo.description ?: "No description",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Forks: ${repo.forks}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Display the total number of forks and add a star badge if forks exceed 5000
        if (totalForks > 5000) {
            Text(
                text = "Total Forks: $totalForks ★",
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            Text(
                text = "Total Forks: $totalForks",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}