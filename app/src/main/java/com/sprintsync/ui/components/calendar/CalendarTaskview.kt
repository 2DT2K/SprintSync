package com.sprintsync.ui.components.calendar

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.theme.spacing
import java.time.Duration
import java.time.LocalDateTime



@SuppressLint("DiscouragedApi")
@Composable
fun CalendarTaskview(
    calendarTask: TaskResDto,
    statusList: List<String>?
) {
    val context: Context = LocalContext.current
    val resources = context.resources
    val packageName = context.packageName
    var dotColor = "blue_dot";
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.small,
                    Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                when (statusList?.get(calendarTask.statusIndex)) {
                    "To Do" -> dotColor = "blue_dot"
                    "In Progress" -> dotColor = "yellow_dot"
//                            "Stuck" -> dotColor = "red_dot"
//                            "Review" -> dotColor = "blue_dot"
                    "Done" -> dotColor = "green_dot"
                }
                val id = resources.getIdentifier(dotColor, "drawable", packageName)
                Image(
                    painter = painterResource(id = id),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                calendarTask.deadline?.let {
                    val dlDate = LocalDateTime.parse(it)
                    val currentDate = LocalDateTime.now()

                    val duration = Duration.between(dlDate, currentDate)
                    val daysDifference = duration.toDays()

                    val taskState = statusList?.get(calendarTask.statusIndex)
                    var displayText = "";
                    if (taskState == "Done") {
                        displayText = "Done"
                    } else {
                        if (daysDifference.toInt() < -1) {
                            displayText = "Late ${daysDifference.toInt()} days"
                        }
                        if (daysDifference.toInt() == -1) {
                            displayText = "Late 1 day"
                        }
                        if (daysDifference.toInt() == 0) {
                            displayText = "Today"
                        }
                        if (daysDifference.toInt() == 1) {
                            displayText = "1 day remaining"
                        }
                        else{
                            displayText = "${daysDifference.toInt()} days remaining"
                        }
                    }


                    Text(
                        text = displayText,
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF8F9BB3),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.75.sp,
                        )
                    )
                }

            }
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .height(16.dp)
                    .width(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.horizental_three_dot),
                    contentDescription = ""
                )
            }

        }
        Text(
            text = calendarTask.name,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 19.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222B45),
                letterSpacing = 1.sp,
            )
        )
        calendarTask.description?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF8F9BB3),
                    letterSpacing = 0.75.sp,
                )
            )
        }

    }
}



//@Preview(showBackground = true)
//@Composable
//fun CalendarTaskviewPreview() {
//    CalendarTaskview(
//        fakeCalendarTask
//    )
//}
