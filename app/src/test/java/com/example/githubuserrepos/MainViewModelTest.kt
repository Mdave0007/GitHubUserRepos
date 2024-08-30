package com.example.githubuserrepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuserrepos.model.Repository
import com.example.githubuserrepos.model.User
import com.example.githubuserrepos.network.UserRepository
import com.example.githubuserrepos.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun fetchUserData_success() = runBlocking {
        // Arrange
        val mockUser = User(name = "Test User", avatar_url = "http://example.com/avatar.png")
        val mockRepos = listOf(
            Repository(name = "Repo1", description = "Description1", forks = 10),
            Repository(name = "Repo2", description = "Description2", forks = 20)
        )

        Mockito.`when`(repository.getUser("testuser")).thenReturn(mockUser)
        Mockito.`when`(repository.getUserRepos("testuser")).thenReturn(mockRepos)

        // Act
        viewModel.fetchUserData("testuser")

        // Assert
        assertEquals(mockUser, viewModel.user.first())
        assertEquals(mockRepos, viewModel.repos.first())
    }
}