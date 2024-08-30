package com.example.githubuserrepos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubuserrepos.network.RetrofitInstance
import com.example.githubuserrepos.network.UserRepository
import com.example.githubuserrepos.viewmodel.MainViewModel
import com.example.githubuserrepos.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = UserRepository(RetrofitInstance.api)
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "main_screen"
            ) {
                composable("main_screen") {
                    MainScreen(viewModel = viewModel, navController = navController)
                }
                composable(
                    "detail_screen/{name}/{description}/{forks}/{totalForks}",
                    arguments = listOf(
                        navArgument("name") { type = NavType.StringType },
                        navArgument("description") { type = NavType.StringType },
                        navArgument("forks") { type = NavType.IntType },
                        navArgument("totalForks") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("name") ?: ""
                    val description = backStackEntry.arguments?.getString("description") ?: "No description"
                    val forks = backStackEntry.arguments?.getInt("forks") ?: 0
                    val totalForks = backStackEntry.arguments?.getInt("totalForks") ?: 0

                    DetailScreen(name = name, description = description, forks = forks, totalForks = totalForks)
                }
            }
        }
    }
}
