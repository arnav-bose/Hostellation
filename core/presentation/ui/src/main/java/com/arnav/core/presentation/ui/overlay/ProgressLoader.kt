package com.arnav.core.presentation.ui.overlay

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressLoader(
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary
    val currentRotation = remember {
        mutableFloatStateOf(0f)
    }
    val animRotate = remember {
        Animatable(currentRotation.floatValue)
    }
    LaunchedEffect(key1 = Unit) {
        animRotate.animateTo(
            360f,
            infiniteRepeatable(
                tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation.floatValue = value
        }
    }

    Canvas(
        modifier = modifier.then(
            Modifier
                .padding(8.dp)
                .rotate(currentRotation.floatValue)
        ), onDraw = {
            drawArc(
                color,
                0f,
                110f,
                false,
                style = Stroke(6.dp.value, cap = StrokeCap.Round)
            )
        })

    Canvas(
        modifier = modifier.then(
            Modifier
                .padding(4.dp)
                .rotate(-currentRotation.floatValue)
        ), onDraw = {
            drawArc(
                color,
                0f,
                320f,
                false,
                style = Stroke(6.dp.value, cap = StrokeCap.Round)
            )
        })
}

@Preview
@Composable
private fun ProgressLoaderPreview() {
    // TODO: Add theme
    ProgressLoader(
        modifier = Modifier.size(32.dp)
    )
}