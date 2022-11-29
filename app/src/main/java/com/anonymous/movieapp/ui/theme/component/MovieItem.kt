package com.anonymous.movieapp.ui.theme.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anonymous.movieapp.domain.movieList.model.MovieDomain


@Composable
fun MovieItem(movie: MovieDomain, onItemSelected: (Long) -> (Unit)) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                onItemSelected.invoke(movie.id)
            }) {
        Row() {

            AsyncImage(
                model = movie.imageURL,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
            )

            Column(Modifier.padding(start = 10.dp)) {
                TitleText(text = movie.title ?: "")
                LabelText(text = movie.releasedYear ?: "")

            }
        }
    }
}
