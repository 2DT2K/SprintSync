package com.sprintsync.ui.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Purple20

@Composable
fun Title(
	title: String,
	subtitle: String
) {
	Surface {
		Column {
			Text(
				text = title,
				style = MaterialTheme.typography.titleLarge,
				color = MaterialTheme.colorScheme.onSurface,
			)
			Text(
				text = subtitle,
				style = MaterialTheme.typography.titleSmall,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
			)
		}
	}
}