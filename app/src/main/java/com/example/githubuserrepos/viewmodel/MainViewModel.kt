package com.example.githubuserrepos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.model.User
import com.example.githubuserrepos.network.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    val totalForks: Flow<Int> = _repos.map { repoList ->
        repoList.sumOf { it.forks }
    }

    fun fetchUserData(userId: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUser(userId)
                val repos = userRepository.getUserRepos(userId)
                _user.value = user
                _repos.value = repos
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching data"
            }
        }
    }
}