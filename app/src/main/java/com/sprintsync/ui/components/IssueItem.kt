package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun IssueItem(
	issueType: String,
	issueDescription: String,
	issueTimeLine: String
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(10.dp),
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.height(52.dp)
			.padding(horizontal = 12.dp, vertical = 7.dp)
	) {
		when (issueType) {
			"Task"    -> Row(
				modifier = Modifier
					.size(36.dp)
					.background(
						color = Color(0xFFEFB1FF),
						shape = RoundedCornerShape(10.dp)
					)
					.padding(6.dp),
				horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Image(
					painter = painterResource(id = R.drawable.check),
					contentDescription = null
				)
			}


			"Project" -> Row(
				modifier = Modifier
					.size(36.dp)
					.background(
						color = Color(0xFFC5B1FF),
						shape = RoundedCornerShape(10.dp)
					)
					.padding(6.dp),
				horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painter = painterResource(id = R.drawable.folder_issue),
					contentDescription = null
				)
			}

			else      -> {}
		}
		Column(modifier = Modifier.fillMaxWidth()) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = issueType,
					style = TextStyle(
						fontSize = 14.sp,
						fontWeight = FontWeight(400),
						color = Color(0xFF000000)
					)
				)
				Text(
					text = issueTimeLine,
					style = TextStyle(
						fontSize = 12.sp,
						fontWeight = FontWeight(400),
						color = Color(0xFF595757),
						textAlign = TextAlign.Right
					)
				)
			}
			Text(
				text = issueDescription,
				style = TextStyle(
					fontSize = 14.sp,
					fontWeight = FontWeight(700),
					color = Color(0xFF000000),
				)
			)
		}


	}
}

@Preview(showBackground = true)
@Composable
fun IssueItemPreview() {
	IssueItem("Task", "Design UI for HomePage", "now")
}


