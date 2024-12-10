package com.arnav.core.presentation.ui

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingSnippet(@IntRange(0, 100) ratingValue: Int) {
    val backgroundColor = when (ratingValue) {
        in 0..50 -> Color.Red
        in 51..75 -> Color.Yellow
        else -> Color.Green
    }
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .padding(2.dp)
    ) {
        Text((ratingValue.toFloat() / 10f).toString(), modifier = Modifier.padding(start = 8.dp, end = 8.dp))
    }
}

@Preview
@Composable
private fun RatingSnippetPreview() {
    RatingSnippet(
        90
    )
}