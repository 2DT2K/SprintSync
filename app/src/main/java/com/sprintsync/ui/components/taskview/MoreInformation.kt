package com.sprintsync.ui.components.taskview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
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
import com.sprintsync.ui.components.SimpleMemberInfor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInformation(
	point: Number,
	assigneeList: List<String>,
	taskTag: List<String>,
	reporter: String
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.padding(4.dp)
			.fillMaxWidth()
	) {
		Row(
			horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(
				verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
				horizontalAlignment = Alignment.Start,
				modifier = Modifier.padding(5.dp)
			) {
				Text(
					text = "Issue Type", style = TextStyle(
						fontSize = 12.sp,
						lineHeight = 16.sp,
						fontWeight = FontWeight(600),
						color = Color(0xB221005D),
					)
				)
				Row(
					horizontalArrangement = Arrangement.spacedBy(
						4.dp,
						Alignment.CenterHorizontally
					),
					verticalAlignment = Alignment.CenterVertically,
				) {
					Image(
						painter = painterResource(id = R.drawable.check_box),
						contentDescription = "",
						modifier = Modifier
							.padding(1.dp)
							.width(20.dp)
							.height(20.dp)
					)
					Text(
						text = "Task", style = TextStyle(
							fontSize = 14.sp,
							lineHeight = 20.sp,
							fontWeight = FontWeight(400),
							color = Color(0xD921005D),
							textAlign = TextAlign.Center,
							letterSpacing = 0.1.sp,
						)
					)
				}
			}
			Divider(
				modifier = Modifier
					.height(53.dp)
					.padding(top = 16.dp, bottom = 16.dp)
					.width(1.dp)
			)
			Column(
				verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
				horizontalAlignment = Alignment.Start,
				modifier = Modifier.padding(5.dp)
			) {
				Text(
					text = "Point", style = TextStyle(
						fontSize = 12.sp,
						lineHeight = 16.sp,
						fontWeight = FontWeight(600),
						color = Color(0xB221005D),
					)
				)
				Text(
					text = point.toString(), style = TextStyle(
						fontSize = 14.sp,
						lineHeight = 20.sp,
						fontWeight = FontWeight(400),
						color = Color(0xD921005D),
						letterSpacing = 0.1.sp,
					)
				)
			}
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.Start,
			modifier = Modifier.padding(5.dp)
		) {
			Text(
				text = "Assignee",
				style = TextStyle(
					fontSize = 12.sp,
					lineHeight = 16.sp,
					fontWeight = FontWeight(600),
					color = Color(0xB221005D),
				)
			)
			Row(
				horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
				verticalAlignment = Alignment.CenterVertically,
			) {
				assigneeList.forEach {
					SimpleMemberInfor(name = it)
				}
			}
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
			horizontalAlignment = Alignment.Start,
			modifier = Modifier.padding(4.dp)
		) {
			Text(
				text = "Labels",
				style = TextStyle(
					fontSize = 12.sp,
					lineHeight = 16.sp,
					fontWeight = FontWeight(600),
					color = Color(0xB221005D),
				)
			)
			Row(
				horizontalArrangement = Arrangement.spacedBy(
					10.dp,
					Alignment.CenterHorizontally
				),
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.padding(top = 0.dp, bottom = 0.dp)
					.height(30.dp)
			) {
				taskTag.forEach {
					SuggestionChip(
						onClick = { /*TODO*/ },
						label = { Text(it) },
						border = null,
						shape = RoundedCornerShape(size = 4.dp),
						colors = SuggestionChipDefaults.suggestionChipColors(
							containerColor = Color(0xFFDCCFE3),
							labelColor = Color(0xFF49454F)
						),
					)
				}
				Button(
					onClick = { /*TODO*/ },
					modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
					contentPadding = PaddingValues(
						start = 8.dp, end = 8.dp, top = 7.dp, bottom = 7.dp
					),
					colors = ButtonDefaults.buttonColors(
						containerColor = Color.Transparent,
						contentColor = Color(0xFF49454F)
					),
					border = BorderStroke(1.dp, Color(0xFF79747E)),
					shape = RoundedCornerShape(size = 4.dp),
				) {
					Text(
						text = "Add ",
						style = TextStyle(
							fontSize = 10.sp,
							lineHeight = 12.2.sp,
							fontWeight = FontWeight(500),
							color = Color(0xFF49454F),
							textAlign = TextAlign.Center,
							letterSpacing = 0.1.sp,
						)
					)
					Image(
						painter = painterResource(id = R.drawable.normal_add),
						contentDescription = "",
						modifier = Modifier.height(18.dp)
					)
				}
			}
		}
		Column(
			verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.Start,
			modifier = Modifier.padding(5.dp)
		) {
			Text(
				text = "Reporter",
				style = TextStyle(
					fontSize = 12.sp,
					lineHeight = 16.sp,
					fontWeight = FontWeight(600),
					color = Color(0xB221005D),
				)
			)
			Row(
				horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
				verticalAlignment = Alignment.CenterVertically,
			) {
				SimpleMemberInfor(name = reporter)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MoreInformationPreview() {
	MoreInformation(
		70,
		listOf("Nguyen Hai Dan", "Tran Chien Thang"),
		listOf("Homepage", "FE"),
		"Vo Tin Du"
	)
}