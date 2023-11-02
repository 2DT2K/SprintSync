package com.sprintsync.ui.components.taskview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun SingleFileAttachment(fileName: String, fileType: String, fileSize: Double) {

	Row(
		horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.border(
				width = 1.dp, color = Color(0xFFCAC4D0), shape = RoundedCornerShape(size = 8.dp)
			)
			.padding(start = 8.dp, top = 5.dp, end = 12.dp, bottom = 5.dp)
	) {
		IconButton(onClick = { /*TODO*/ }) {
			when (fileType) {
				"pdf" -> Image(
					painter = painterResource(id = R.drawable.pdf_icon),
					contentDescription = "",
					contentScale = ContentScale.None,
					modifier = Modifier
						.padding(1.dp)
						.width(32.dp)
						.height(32.dp)
				)

				"png" -> Image(
					painter = painterResource(id = R.drawable.png_icon),
					contentDescription = "",
					contentScale = ContentScale.None,
					modifier = Modifier
						.padding(1.dp)
						.width(32.dp)
						.height(32.dp)
				)
			}
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.Start,
		) {
			Row(horizontalArrangement = Arrangement.Center) {
				Text(
					text = fileName, modifier = Modifier.widthIn(max = 45.dp), style = TextStyle(
						fontSize = 10.sp,
						lineHeight = 16.sp,
						fontWeight = FontWeight(500),
						color = Color(0xFF000000),
					), maxLines = 1, overflow = TextOverflow.Ellipsis
				)
				Text(
					text = ".$fileType",
					style = TextStyle(
						fontSize = 10.sp,
						lineHeight = 16.sp,
						fontWeight = FontWeight(500),
						color = Color(0xFF000000),
					),
				)
			}

			Text(
				text = fileSize.toString() + "MB", style = TextStyle(
					fontSize = 8.sp,
					lineHeight = 8.sp,
					fontWeight = FontWeight(400),
					color = Color(0xFF7B7B7B),
				)
			)
		}
		Row(
			horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.width(20.dp)
				.height(20.dp)
				.background(color = Color(0xFFE8DEF8), shape = RoundedCornerShape(size = 8.dp))
				.padding(4.dp)
		) {
			IconButton(onClick = { /*TODO*/ }) {
				Image(
					painter = painterResource(id = R.drawable.cancel_icon),
					contentDescription = ""
				)
			}
		}

	}
}


@Preview(showBackground = true)
@Composable
fun SingleFileAttachmentPreview() {
	SingleFileAttachment(fileName = "Test", fileType = "pdf", fileSize = 6.8)
}