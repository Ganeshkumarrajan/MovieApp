package com.anonymous.movieapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.movieapp.domain.movieList.model.MovieDomain
import com.anonymous.movieapp.domain.movieList.useCase.GetMovieListUseCase
import com.anonymous.movieapp.presentation.movieList.viewModel.MovieListViewModel
import com.anonymous.movieapp.presentation.utils.ScreenState
import com.anonymous.movieapp.util.MainCoroutineRule
import com.anonymous.movieapp.util.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class MovieListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val useCase: GetMovieListUseCase = mockk()
    private var viewModel: MovieListViewModel = MovieListViewModel(useCase)


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMovies() {
        runTest {
            viewModel.state.test(this).use {

                coEvery {
                    useCase()
                } returns getFakeResult()

                viewModel.getMovies()

                val expected = listOf(ScreenState.Loading(), ScreenState.Success(getFakeResult()))

                assertEquals(it.values[1], expected[1])
            }
        }
    }

    private fun getFakeResult(): List<MovieDomain> =
        listOf(
            MovieDomain(
                id = 12345,
                title = "this is title 1",
                imageURL = "path",
                releasedYear = "2022"
            ),
            MovieDomain(
                id = 12345,
                title = "this is title 1",
                imageURL = "path",
                releasedYear = "2022"
            )
        )
}
