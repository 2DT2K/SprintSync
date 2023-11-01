package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun CustomIconButton(iconID: Int, content: String, backgroundColor: Long, modifier: Modifier) {
	Row(
		modifier = modifier
			.padding(0.dp)
			.height(115.dp)
			.background(
				color = Color(backgroundColor),
				shape = RoundedCornerShape(size = 20.dp)
			)
			.padding(start = 16.dp, end = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(
			10.dp,
			Alignment.Start
		),
	) {
		Box(
			modifier = Modifier
				.background(
					color = Color(0x4FF3FFFE),
					shape = RoundedCornerShape(size = 10.dp)
				)
		) {
			Image(
				modifier = Modifier
					.align(Alignment.Center)
					.padding(10.dp),
				painter = painterResource(id = iconID),
				contentDescription = ""
			)
		}
		Text(
			text = content, style = TextStyle(
				fontSize = 20.sp,
				fontWeight = FontWeight(500),
				color = Color(0xFFFFFFFF)
			)
		)
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

