package com.sprintsync.ui.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.theme.spacing

@Composable
fun PromptRow(
	modifier: Modifier = Modifier.height(40.dp),
	normalText: String,
	highlightedText: String,
	highlightColor: Color,
	onClick: () -> Unit
) {
	Row(
		modifier = modifier,
		horizontalArrangement = Arrangement.Center
	) {
		Text(
			text = normalText,
			style = MaterialTheme.typography.bodyLarge,
			color = MaterialTheme.colorScheme.onSurfaceVariant,
		)

		Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

		Text(
			modifier = Modifier.clickable(
				interactionSource = MutableInteractionSource(),
				indication = rememberRipple(
					bounded = true,
					radius = 250.dp
				),
				onClick = onClick
			),
			text = highlightedText,
			style = MaterialTheme.typography.titleSmall,
			color = highlightColor,
		)
	}
}