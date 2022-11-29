package com.anonymous.movieapp.presentation.movieDetails.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.presentation.utils.ScreenState
import com.anonymous.movieapp.presentation.movieDetails.viewModel.MovieDetailsViewModel
import com.anonymous.movieapp.ui.theme.component.*

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel = hiltViewModel(), movieID: Long) {
    val result = viewModel.movieDetails.collectAsState().value
    LazyColumn() {
        item {
            when (result) {
                is ScreenState.Success -> {
                    OnSuccess(result.data)
                }
                is ScreenState.Loading -> {
                    LoadingIndicator()
                }
                is ScreenState.Error -> {
                    ErrorView()
                }
            }
        }
    }

    viewModel.getMovieDetails(movieID)
}

@Composable
private fun OnSuccess(movieDetailsDomain: MovieDetailsDomain) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        AsyncImage(
            model = movieDetailsDomain.imageURl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))
        TitleText(text = movieDetailsDomain.title ?: "")
        Spacer(modifier = Modifier.padding(top = 2.dp))
        LabelText(text = movieDetailsDomain.yearOfRelease ?: "")
        Spacer(modifier = Modifier.padding(top = 5.dp))
        SubTitleText(text = movieDetailsDomain.description ?: "")
    }

}