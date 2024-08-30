package com.example.githubuserrepos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.model.User
import com.example.githubuserrepos.network.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel class responsible for managing UI-related data in a lifecycle-conscious way
class MainViewModel(private val repository: UserRepository) : ViewModel() {

    // StateFlow to hold user data fetched from the API
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    // StateFlow to hold a list of repositories fetched from the API
    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos

    // Function to fetch user and repository data based on the provided GitHub user ID
    fun fetchUserData(userId: String) {
        // Launching a coroutine in viewModelScope for performing asynchronous operations
        viewModelScope.launch {
            try {
                // Fetching user data from the repository
                val userData = repository.getUser(userId)

                // Fetching repository data from the repository
                val reposData = repository.getUserRepos(userId)

                // Updating the StateFlow objects with the fetched data
                _user.value = userData
                _repos.value = reposData
            } catch (e: Exception) {
                // Handle exceptions (e.g., network errors)
                // Consider adding error handling code here to update the UI accordingly
            }
        }
    }
}