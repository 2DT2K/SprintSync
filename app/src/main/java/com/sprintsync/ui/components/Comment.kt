package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun Comment(
	commenter: String, commentTime: String, content: String, avatar: @Composable () -> Unit = {
		Image(
			painter = painterResource(id = R.drawable.avataricon),
			contentDescription = "image description",
			contentScale = ContentScale.FillBounds
		)
	}
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
		verticalAlignment = Alignment.Top,
		modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
	) {
		Row(
			horizontalArrangement = Arrangement.spacedBy(
				0.dp,
				Alignment.CenterHorizontally
			),
			modifier = Modifier
				.width(32.dp)
				.height(32.dp),
			verticalAlignment = Alignment.CenterVertically,
		) {
			avatar()
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(
				0.dp,
				Alignment.CenterVertically
			),
			horizontalAlignment = Alignment.Start,
		) {
			Row(
				horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Text(
					text = commenter,
					style = TextStyle(
						fontSize = 12.sp,
						lineHeight = 16.sp,
						fontWeight = FontWeight(500),
						color = Color(0xFF000000),
					)
				)
				Text(
					text = commentTime,
					style = TextStyle(
						fontSize = 10.sp,
						lineHeight = 8.sp,
						fontWeight = FontWeight(400),
						color = Color(0xFF7B7B7B),
					)
				)
			}
			Text(
				text = content,
				style = TextStyle(
					fontSize = 12.sp,
					lineHeight = 14.4.sp,
					fontWeight = FontWeight(400),
					color = Color(0xFF7B7B7B),
					letterSpacing = 0.5.sp,
				)
			)
		}
	}
}