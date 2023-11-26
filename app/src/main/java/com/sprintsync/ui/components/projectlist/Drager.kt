package com.sprintsync.ui.components.projectlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Drager() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		Box(
			modifier = Modifier
				.padding(0.dp)
				.width(32.dp)
				.height(4.dp)
				.background(
					color = Color(0xFF79747E),
					shape = RoundedCornerShape(size = 100.dp)
				)
		)
	}
}