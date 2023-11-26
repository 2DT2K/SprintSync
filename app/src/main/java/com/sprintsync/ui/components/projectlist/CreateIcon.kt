package com.sprintsync.ui.components.projectlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Grey80

@Composable
fun CreateIcon(
	onClick: () -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(end = 8.dp)
	) {
		IconButton(
			modifier = Modifier
				.align(Alignment.CenterEnd)
				.background(
					color = Grey80,
					shape = RoundedCornerShape(100)
				),
			onClick = onClick
		) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(start = 4.dp)
					.size(44.dp),
				contentAlignment = Alignment.Center
			) {
				Icon(
					modifier = Modifier
						.padding(0.dp)
						.size(32.dp),
					painter = painterResource(id = R.drawable.send),
					contentDescription = "create project",
					tint = Grey40
				)
			}
		}
	}
}