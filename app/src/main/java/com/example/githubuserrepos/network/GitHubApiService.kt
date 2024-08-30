package com.example.githubuserrepos.network

import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.model.User
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit service interface for making GitHub API calls
interface GitHubApiService {

    // API call to get user information based on the user ID
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

    // API call to get the list of repositories for a specific user
    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") userId: String): List<Repository>
}