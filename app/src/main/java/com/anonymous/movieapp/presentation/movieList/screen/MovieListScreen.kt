package com.anonymous.movieapp.presentation.movieList.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anonymous.movieapp.domain.movieList.model.MovieDomain
import com.anonymous.movieapp.presentation.utils.ScreenState
import com.anonymous.movieapp.presentation.movieList.viewModel.MovieListViewModel
import com.anonymous.movieapp.presentation.utils.ScreenNames
import com.anonymous.movieapp.ui.theme.component.ErrorView
import com.anonymous.movieapp.ui.theme.component.LoadingIndicator
import com.anonymous.movieapp.ui.theme.component.MovieItem

@Composable
fun MovieListScreen( nav: NavHostController,viewModel: MovieListViewModel = hiltViewModel()) {
    val movieListState = viewModel.state.collectAsState().value

    Column(Modifier.padding(10.dp)) {
        when (movieListState) {
            is ScreenState.Success -> {
                OnSuccess(movieListState.data, nav)
            }
            is ScreenState.Loading -> {
                LoadingIndicator()
            }
            is ScreenState.Error -> {
                ErrorView()
            }
        }
    }

    viewModel.getMovies()
}

@Composable
private fun OnSuccess(movies: List<MovieDomain>, nav: NavHostController) {
    LazyColumn() {
        items(movies) { movie ->
            MovieItem(movie = movie, onItemSelected = {
                nav.navigate("${ScreenNames.Details.route}/$it")
            })
        }
    }
}
