package com.sprintsync.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider() {
	Column(
		verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
		horizontalAlignment = Alignment.Start,
		modifier = Modifier.padding(start = 5.dp, end = 5.dp)
	) {
		Divider()
	}
}