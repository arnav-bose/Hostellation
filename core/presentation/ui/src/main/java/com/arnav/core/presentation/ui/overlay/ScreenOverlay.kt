package com.arnav.core.presentation.ui.overlay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.arnav.core.domain.DataError
import com.arnav.core.domain.ScreenOverlayData
import com.arnav.core.presentation.ui.asUIText

@Composable
fun ScreenOverlay(modifier: Modifier = Modifier, data: ScreenOverlayData) {
    when (data) {
        is ScreenOverlayData.Error -> ScreenOverlayError(modifier, data)
        ScreenOverlayData.Loading -> ScreenOverlayProgressLoader(modifier)
        ScreenOverlayData.None -> Unit
    }
}

@Composable
private fun ScreenOverlayError(modifier: Modifier = Modifier, data: ScreenOverlayData) {
    Column(modifier = modifier) {
        Text(
            (data as? ScreenOverlayData.Error)?.error?.asUIText()?.asString() ?: "",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = TextUnit(14f, TextUnitType.Sp)
        )
    }
}

@Composable
fun ScreenOverlayProgressLoader(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        ProgressLoader(
            modifier = Modifier
                .align(Alignment.Center)
                .size(64.dp)
        )
    }
}

@Preview
@Composable
private fun ScreenOverlayPreview(
    modifier: Modifier = Modifier,
    data: ScreenOverlayData = ScreenOverlayData.Error(DataError.Network.NO_INTERNET)
) {
    // TODO: Add Theme
    ScreenOverlay(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.White
            ), data = data
    )
}