package com.sprintsync.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val small: Dp = 4.dp,
    val default: Dp = 8.dp,
    val largeDefault: Dp = 10.dp,
    val smallMedium: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val mediumLarge: Dp = 20.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    get() = LocalSpacing.current