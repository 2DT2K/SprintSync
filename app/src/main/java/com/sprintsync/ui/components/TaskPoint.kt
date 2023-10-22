package com.sprintsync.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskPoint(point: Number, modifier: Modifier) {
	Row(
		modifier = modifier

	) {
		Text(
			text = point.toString(),
			modifier = Modifier.padding(
				start = 5.dp,
				top = 2.dp,
				end = 5.dp,
				bottom = 2.dp
			)
		)
	}
}