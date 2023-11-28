package com.sprintsync.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sprintsync.ui.theme.spacing

@Composable
fun TaskPoint(point: Number, modifier: Modifier) {
    Box(
        modifier = modifier.padding(
            vertical = MaterialTheme.spacing.small,
			horizontal = MaterialTheme.spacing.default
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = point.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}