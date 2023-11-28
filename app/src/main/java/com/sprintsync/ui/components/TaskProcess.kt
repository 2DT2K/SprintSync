package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TaskProcess(
    title: String,
	color: Long,
	verticalPadding: Dp = 4.dp,
	horizontalPadding: Dp = 8.dp
) {
    Box(
        modifier = Modifier
			.background(
				color = Color(color),
				shape = RoundedCornerShape(size = 6.dp)
			)
			.padding(
				vertical = verticalPadding,
				horizontal = horizontalPadding
			)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            color = MaterialTheme.colorScheme.surface,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskProcesspreview() {
    TaskProcess(title = "In Progress", color = 0xFFF7C84F)
}