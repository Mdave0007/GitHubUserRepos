package com.example.githubuserrepos.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object to create and provide Retrofit instance
object RetrofitInstance {

    // Base URL for GitHub API
    private const val BASE_URL = "https://api.github.com/"

    // Lazy-initialized Retrofit instance with Gson converter
    val api: GitHubApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }
}