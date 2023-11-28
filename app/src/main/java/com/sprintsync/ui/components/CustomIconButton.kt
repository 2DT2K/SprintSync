package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing

@Composable
fun CustomIconButton(
    iconID: Int,
	content: String,
	backgroundColor: Long,
	onClick: () -> Unit = {},
	modifier: Modifier
) {
	Surface(
		onClick = onClick,
		modifier = modifier
	) {
		Row(
			modifier = Modifier
				.padding(0.dp)
				.height(80.dp)
				.background(
					color = Color(backgroundColor),
					shape = RoundedCornerShape(size = 24.dp)
				)
				.padding(
					start = MaterialTheme.spacing.medium,
					end = MaterialTheme.spacing.smallMedium
				),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(
				MaterialTheme.spacing.default,
				Alignment.Start
			),
		) {
			Box(
				modifier = Modifier
					.background(
						color = Color(0x4FF3FFFE),
						shape = RoundedCornerShape(size = 12.dp)
					)
			) {
				Image(
					modifier = Modifier
						.align(Alignment.Center)
						.padding(MaterialTheme.spacing.default),
					painter = painterResource(id = iconID),
					contentDescription = ""
				)
			}
			Text(
				text = content,
				style = MaterialTheme.typography.titleMedium,
				color = MaterialTheme.colorScheme.onPrimary,
				maxLines = 1
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun CustomIconButtonPreview() {
    CustomIconButton(
        iconID = R.drawable.timelapse,
        content = "Timeline",
        backgroundColor = 0xFFFF829E,
        modifier = Modifier
			.padding(0.dp)
			.height(115.dp)
			.background(
				color = Color(0xFFFF829E),
				shape = RoundedCornerShape(size = 20.dp)
			)
			.padding(start = 16.dp, end = 16.dp)
    )
}

