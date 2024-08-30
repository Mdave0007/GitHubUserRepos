package com.example.githubuserrepos.model

import com.google.gson.annotations.SerializedName

// Data class representing a GitHub repository
data class Repository(
    val name: String,
    val description: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    val forks: Int
)