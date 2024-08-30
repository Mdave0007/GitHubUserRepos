package com.example.githubuserrepos.model

// Data class representing a GitHub repository
data class Repository(
    val name: String,
    val description: String?,
    val forks: Int
)