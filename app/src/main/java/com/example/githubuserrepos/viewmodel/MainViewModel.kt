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

    // StateFlow to hold any error messages for display in the UI
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Function to fetch user and repository data based on the provided GitHub user ID
    fun fetchUserData(userId: String) {
        // Resetting the error message at the start of the operation
        _errorMessage.value = null

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
                // If any exception occurs, set the error message for the UI to display
                _errorMessage.value = "Failed to fetch data: ${e.message}"
            }
        }
    }
}