package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChartInfor(remaining: Int, completed: Int, remainingColor: Color, completedColor: Color) {
	Column(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
		horizontalAlignment = Alignment.Start
	) {
		WorkItem("Remaining work", remaining.toString(), remainingColor)
		WorkItem("Completed work", completed.toString(), completedColor)
	}
}

@Composable
fun WorkItem(title: String, count: String, color: Color) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.width(4.dp)
				.height(20.dp)
				.background(color = color, shape = RoundedCornerShape(3.dp))
		) {
		}
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = title,
				style = TextStyle(
					fontSize = 12.sp,
					lineHeight = 14.4.sp,
					fontWeight = FontWeight(500),
					color = Color(0xFF7B7B7B)
				)
			)
			Text(
				text = count,
				style = TextStyle(
					fontSize = 14.sp,
					lineHeight = 16.8.sp,
					fontWeight = FontWeight(400),
					color = Color(0xFF7B7B7B),
					textAlign = TextAlign.Right
				)
			)
		}
	}
}