package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun SimpleMemberInfor(name: String) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(
			4.dp,
			Alignment.CenterHorizontally
		),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Image(
			painter = painterResource(id = R.drawable.avataricon),
			contentDescription = "",
			modifier = Modifier.width(24.dp)
		)
		Text(
			text = name,
			style = TextStyle(
				fontSize = 15.sp,
				lineHeight = 20.sp,
				fontWeight = FontWeight(400),
				color = Color(0xD921005D),
				letterSpacing = 0.1.sp,
			)
		)
	}
}