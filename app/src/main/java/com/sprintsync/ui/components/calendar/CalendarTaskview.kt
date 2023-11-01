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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import java.time.LocalDateTime
import java.time.Month

data class CalendarTask(
    val taskState: String,
    val taskDeadline: String,
    val taskName: String,
    val taskDescription: String,
    var deadline: LocalDateTime
)

@SuppressLint("DiscouragedApi")
@Composable
fun CalendarTaskview(
    calendarTask: CalendarTask
) {
    val context: Context = LocalContext.current
    val resources = context.resources
    val packageName = context.packageName
    var dotColor = "blue_dot";
    print("AGGJHSGDHJSGDHJSGDHJSGDHJ")
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
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
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                when (calendarTask.taskState) {
                    "Todo" -> dotColor = "blue_dot"
                    "In Progress" -> dotColor = "blue_dot"
                    "Stuck" -> dotColor = "red_dot"
                    "Review" -> dotColor = "blue_dot"
                    "Done" -> dotColor = "green_dot"
                }
                val id = resources.getIdentifier(dotColor, "drawable", packageName)
                Image(
                    painter = painterResource(id=id),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Text(
                    text = calendarTask.taskDeadline,
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
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                .height(16.dp)
                .width(20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.horizental_three_dot),
                    contentDescription = ""
                )
            }

        }
        Text(
            text = calendarTask.taskName,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 19.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222B45),
                letterSpacing = 1.sp,
            )
        )
        Text(
            text = calendarTask.taskDescription,
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

var fakeCalendarTask = CalendarTask("Todo", "2 day remaining", "Play Dota", "Reach 5k mmr",
    LocalDateTime.of(2023, Month.NOVEMBER,1,0,0))

@Preview(showBackground = true)
@Composable
fun CalendarTaskviewPreview() {
    CalendarTaskview(
        fakeCalendarTask
    )
}
