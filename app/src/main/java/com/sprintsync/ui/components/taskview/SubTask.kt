package com.sprintsync.ui.components.taskview

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.ui.components.TaskProcess


class SubTask(
	val status: String,
	val taskNavigation: String,
	val taskName: String,
	val assignees: MutableList<Bitmap>,
)


@Composable
fun SubTask(subTaskList: List<SubTask>) {
	val context = LocalContext.current
	// Add ImageBitmap objects to the list

	Column(
		verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
		horizontalAlignment = Alignment.Start,
		modifier = Modifier
			.fillMaxWidth()
			.padding(5.dp)
	) {
		Text(
			text = "Sub Tasks",
			style = TextStyle(
				fontSize = 12.sp,
				lineHeight = 16.sp,
				fontWeight = FontWeight(600),
				color = Color(0xB221005D),
			)
		)
		subTaskList.forEach {
			Row(
				horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.fillMaxWidth()
			) {
				Row(
					horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
					verticalAlignment = Alignment.CenterVertically,
				) {
					Image(
						painter = painterResource(id = R.drawable.check_box),
						contentDescription = "",
						contentScale = ContentScale.None,
						modifier = Modifier
							.padding(1.dp)
					)
					Text(
						text = it.taskNavigation,
						style = TextStyle(
							fontSize = 13.33.sp,
							lineHeight = 16.sp,
							fontWeight = FontWeight(500),
							color = Color(0xD95E4E79),
							letterSpacing = 0.1.sp,
						)
					)
				}
				Row(
					horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
					verticalAlignment = Alignment.CenterVertically,
				) {
					Text(
						text = it.taskName,
						modifier = Modifier.width(110.dp),
						style = TextStyle(
							fontSize = 14.sp,
							lineHeight = 16.sp,
							fontWeight = FontWeight(400),
							color = Color(0xFF21005D),
							letterSpacing = 0.5.sp,
						),
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
					)
				}
				Row(
					horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(end = 8.dp)
						.weight(1f)
				) {
					Row(
						horizontalArrangement = Arrangement.spacedBy((-14).dp, Alignment.End),
						verticalAlignment = Alignment.CenterVertically,
					) {
						ContextCompat
							.getDrawable(context, R.drawable.nice_avartar)
							?.let { it1 ->
								it.assignees.add(
									it1.toBitmap()
								)
							}
						it.assignees.forEachIndexed { index, image ->
							Image(
								bitmap = image.asImageBitmap(),
								contentDescription = "",
								modifier = Modifier
									.zIndex(index.toFloat())
									.width(24.dp)
									.height(24.dp)
							)
						}
					}
					when (it.status) {
						"In progress"  -> TaskProcess(title = "In progress", color = 0xFFF7C84F)
						"Productivity" -> TaskProcess(title = "Productivity", color = 0xFFAA60AB)
						"Todo"         -> TaskProcess(title = "Todo", color = 0xFF4CF590)
					}
				}
			}
		}
	}
}

var subTask1 = SubTask(
	status = "In progress",
	taskName = "Study MonggoDB",
	taskNavigation = "SCRUMMER-1",
	assignees = mutableListOf(),
)
var subTask2 = SubTask(
	status = "Productivity",
	taskName = "Some longgggggggggggggggggggggggggggggggggggggggggg",
	taskNavigation = "SCRUMMER-1",
	assignees = mutableListOf(),
)
var subTask3 = SubTask(
	status = "Todo",
	taskName = "Play dota",
	taskNavigation = "SCRUMMER-1",
	assignees = mutableListOf(),
)
var fakeData = listOf<SubTask>(subTask1, subTask2, subTask3)

@Preview(showBackground = true)
@Composable
fun SubTaskPreview() {
	SubTask(fakeData)
}