package com.anonymous.movieapp.ui.theme.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.anonymous.movieapp.R

@Composable
fun TitleText(text: String) {
    Text(
        style = TextStyle(
            color = colorResource(id = R.color.black),
        ),
        text = text,
        fontSize = 24.sp
    )
}

@Composable
fun SubTitleText(text: String) {
    Text(
        style = TextStyle(
            color = colorResource(id = R.color.black),
        ),
        text = text,
        fontSize = 12.sp
    )
}

@Composable
fun LabelText(text: String) {
    Text(
        style = TextStyle(
            color = colorResource(id = R.color.black),
        ),
        text = text,
        fontSize = 18.sp,
    )
}
