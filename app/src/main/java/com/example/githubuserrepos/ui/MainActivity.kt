package com.example.githubuserrepos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserrepos.network.RetrofitInstance
import com.example.githubuserrepos.network.UserRepository
import com.example.githubuserrepos.viewmodel.MainViewModel
import com.example.githubuserrepos.viewmodel.MainViewModelFactory

// MainActivity that sets up the UI content using Jetpack Compose
class MainActivity : ComponentActivity() {

    // ViewModel for the MainActivity, now created using a custom factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an instance of UserRepository
        val repository = UserRepository(RetrofitInstance.api)

        // Create an instance of MainViewModel using the custom factory
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {
            // Setting the content of the activity with the MainScreen composable function
            MainScreen(viewModel = viewModel)
        }
    }
}
