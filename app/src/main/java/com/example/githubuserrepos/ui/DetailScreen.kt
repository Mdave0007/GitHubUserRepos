package com.example.githubuserrepos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    name: String,
    description: String,
    forks: Int,
    totalForks: Int
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = name, style = MaterialTheme.typography.h4)
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Forks: $forks",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = if (totalForks > 5000) "Total Forks: $totalForks ★" else "Total Forks: $totalForks",
            style = MaterialTheme.typography.body1,
            color = if (totalForks > 5000) Color.Red else Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}