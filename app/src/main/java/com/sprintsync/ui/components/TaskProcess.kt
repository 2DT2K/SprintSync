package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskProcess(title: String, color: Long) {
	Box(
		modifier = Modifier
			.background(
				color = Color(color),
				shape = RoundedCornerShape(size = 4.dp)
			)
			.padding(start = 8.dp, top = 3.dp, bottom = 3.dp, end = 8.dp)
	) {
		Text(
			text = title,
			style = TextStyle(
				fontSize = 10.sp,
				lineHeight = 12.sp,
				fontWeight = FontWeight(500),
				color = Color(0xFFFFFFFF),
				textAlign = TextAlign.Center,
				letterSpacing = 0.1.sp,
			),
			maxLines = 1,
		)
	}
}

@Preview(showBackground = true)
@Composable
fun TaskProcesspreview() {
	TaskProcess(title = "In Progress", color = 0xFFF7C84F)
}