package com.sprintsync.ui.components.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.IssueItem

data class MenuIssue(
	val issueType: String,
	val issueName: String,
	val issueTime: String
)

val issueList: List<MenuIssue> = listOf(
	MenuIssue(
		issueType = "Task",
		issueName = "Finish Sprint1",
		issueTime = "now"
	),
	MenuIssue(
		issueType = "Task",
		issueName = "Finish all home work",
		issueTime = "1 days ago"
	),
	MenuIssue(
		issueType = "Task",
		issueName = "Reach 5k mmr again",
		issueTime = "2 days ago"
	),
	MenuIssue(
		issueType = "Project",
		issueName = "SprintSync",
		issueTime = "3 days ago"
	),
	MenuIssue(
		issueType = "Project",
		issueName = "No idea",
		issueTime = "1 week ago"
	)
)

@Composable
fun HomePageIssue() {
	val scrollState = rememberScrollState()
	Column(
		verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
		horizontalAlignment = Alignment.CenterHorizontally,

		) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text(
				text = "Recent Issues", style = TextStyle(
					fontSize = 24.sp,
					fontWeight = FontWeight(700),
					color = Color(0xFF1D192B)
				)
			)
			Row(
				modifier = Modifier.background(
					color = Color(0xFFF3EDF7),
					shape =
					RoundedCornerShape(5.dp)
				)
			) {
				IconButton(onClick = { /*TODO*/ }) {
					Image(
						painter = painterResource(id = R.drawable.three_dot),
						contentDescription = ""
					)
				}
			}

		}
		Column(
			modifier = Modifier
				.padding(0.dp)
				.fillMaxWidth()
				.heightIn(max = 250.dp)
				.background(color = Color(0xFFF3EDF7), shape = RoundedCornerShape(size = 15.dp))
				.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
				.verticalScroll(state = scrollState),
			verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			issueList.forEach { item ->
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					IssueItem(
						issueType = item.issueType,
						issueDescription = item.issueName,
						issueTimeLine = item.issueTime
					)
					if (issueList.size - 1 >= 1 && item != issueList[issueList.size - 1]) {
						Divider(modifier = Modifier.fillMaxWidth(0.9f))
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MenuIssuePreview() {
	HomePageIssue()
}