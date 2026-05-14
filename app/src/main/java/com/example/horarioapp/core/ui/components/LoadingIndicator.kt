package com.example.horarioapp.core.ui.components


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    strokeWidth: Dp = 4.dp
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        strokeWidth = strokeWidth,
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
    )
}

@Composable
fun SmallLoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 20.dp,
    strokeWidth: Dp = 2.dp
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        strokeWidth = strokeWidth,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun FullScreenLoadingIndicator() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
            .size(width = 9999.dp, height = 9999.dp),
        contentAlignment = Alignment.Center
    ) {
        LoadingIndicator()
    }
}