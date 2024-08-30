package com.example.githubuserrepos.network

import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.model.User

// Repository class that manages the API calls and provides data to the ViewModel
class UserRepository(private val apiService: GitHubApiService) {

    // Function to fetch user data from the API
    suspend fun getUser(userId: String): User {
        return apiService.getUser(userId)
    }

    // Function to fetch repositories data from the API
    suspend fun getUserRepos(userId: String): List<Repository> {
        return apiService.getUserRepos(userId)
    }
}