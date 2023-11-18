package com.sprintsync.ui.components

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.calendar.CalendarTask
import com.sprintsync.ui.components.calendar.CalendarTaskview
import com.sprintsync.ui.components.calendar.fakeCalendarTask
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.DayOfWeek
import java.time.DayOfWeek.MONDAY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle.FULL
import java.util.Locale

var fakeCalendarTask3 = CalendarTask(
	"Stuck", "1 day remaining", "Play AOE", "Reach 5k mmr",
	LocalDateTime.of(2023, Month.NOVEMBER, 1, 0, 0)
)
var fakeCalendarTask2 = CalendarTask(
	"Done", "2 day remaining", "Play Genshin", "Reach 5k mmr",
	LocalDateTime.of(2023, Month.NOVEMBER, 1, 0, 0)
)
var fakeCalendarTask4 = CalendarTask(
	"Stuck", "1 day remaining", "Play AOE", "Reach 5k mmr",
	LocalDateTime.of(2023, Month.NOVEMBER, 12, 0, 0)
)
var fakeCalendarTask5 = CalendarTask(
	"Done", "2 day remaining", "Play Genshin", "Reach 5k mmr",
	LocalDateTime.of(2023, Month.NOVEMBER, 15, 0, 0)
)
val fakeCalendarList = listOf<CalendarTask>(
	fakeCalendarTask, fakeCalendarTask2, fakeCalendarTask3, fakeCalendarTask4, fakeCalendarTask5
)

@Composable
fun Calendar() {
	val (currentDaySelected, setCurrentDaySelected) = remember {
		mutableStateOf(LocalDateTime.now())
	}
	Column(
		verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
		horizontalAlignment = Alignment.Start,
	) {
		SelectableCalendar(
			modifier = Modifier.animateContentSize(),
			showAdjacentMonths = true,
			firstDayOfWeek = MONDAY,
			dayContent = {
				DayContent(
					dayState = it,
					currentDaySelected = currentDaySelected,
					setCurrentDaySelected = setCurrentDaySelected
				)
			},
			daysOfWeekHeader = { WeekHeader(daysOfWeek = it) },
			monthHeader = { MonthHeader(monthState = it) },
			today = LocalDate.now(),
			horizontalSwipeEnabled = true,
			calendarState = rememberSelectableCalendarState(
				initialMonth = YearMonth.now(),
				initialSelectionMode = SelectionMode.Single,
				initialSelection = listOf(LocalDate.now())
			)
		)
		Column(
			verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
			horizontalAlignment = Alignment.Start,
		) {
			fakeCalendarList.forEach {
				if (currentDaySelected.month == it.deadline.month
					&& currentDaySelected.dayOfMonth == it.deadline.dayOfMonth
					&& currentDaySelected.year == it.deadline.year
				) {
					CalendarTaskview(calendarTask = it)
				}
			}
		}

	}

}

@Composable
private fun DayContent(
	dayState: DayState<DynamicSelectionState>,
	currentDaySelected: LocalDateTime,
	setCurrentDaySelected: (LocalDateTime) -> Unit,
) {
	var paddingValue = 10;
	val context: Context = LocalContext.current
	val resources = context.resources
	val packageName = context.packageName
	val date = dayState.date
	val selectionState = dayState.selectionState
	val isSelected = selectionState.isDateSelected(date)
	var color = if (isSelected) 0xFFFFFFFF else 0xFF222B45
	val isCurrentMonth = dayState.isFromCurrentMonth
	if (!isCurrentMonth) {
		color = 0xFF8F9BB3
	}
	fakeCalendarList.forEach {
		if (date.month == it.deadline.month
			&& date.dayOfMonth == it.deadline.dayOfMonth
			&& date.year == it.deadline.year
		) {
			paddingValue = 4
		}
	}
	Box(modifier = Modifier.then(if (isCurrentMonth) Modifier.clickable {
		selectionState.onDateSelected(date)
		setCurrentDaySelected(LocalDateTime.of(date.year, date.month, date.dayOfMonth, 0, 0))
	}
	else Modifier)) {
		Column(
			verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Text(
				text = dayState.date.dayOfMonth.toString(), style = TextStyle(
					fontSize = 15.sp,
					lineHeight = 20.sp,
					fontWeight = FontWeight(500),
					color = Color(color),
					textAlign = TextAlign.Center,
				), textAlign = TextAlign.Center, modifier = Modifier
					.fillMaxWidth()
					.padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = paddingValue.dp)
					.then(
						if (isSelected) Modifier.background(
							color = Color(0xFF735BF2), shape = RoundedCornerShape(size = 8.dp)
						)
						else Modifier.background(Color.Transparent)
					)
					.padding(4.dp)
			)
			Row(
				horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.height(6.dp)
			) {
				fakeCalendarList.forEach {
					if (date.month == it.deadline.month
						&& date.dayOfMonth == it.deadline.dayOfMonth
						&& date.year == it.deadline.year
					) {
						var dotColor = "blue_dot"
						when (it.taskState) {
							"Todo"        -> dotColor = "blue_dot"
							"In Progress" -> dotColor = "blue_dot"
							"Stuck"       -> dotColor = "red_dot"
							"Review"      -> dotColor = "blue_dot"
							"Done"        -> dotColor = "green_dot"
						}
						val id = resources.getIdentifier(dotColor, "drawable", packageName)
						Image(
							modifier = Modifier.size(6.dp),
							painter = painterResource(id = id),
							contentDescription = ""
						)
					}
				}
			}

		}
	}
}

@Composable
private fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
	Row {
		daysOfWeek.forEach { dayOfWeek ->
			Text(
				textAlign = TextAlign.Center,
				style = TextStyle(
					fontSize = 13.sp,
					lineHeight = 16.sp,
					fontWeight = FontWeight(500),
					color = Color(0xFF8F9BB3),
					textAlign = TextAlign.Center,
				),
				text = dayOfWeek.getDisplayName(FULL, Locale.ROOT),
				modifier = Modifier
					.weight(1f)
					.wrapContentHeight()
			)
		}
	}
}

@Composable
private fun MonthHeader(monthState: MonthState) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
	) {
		IconButton(
			onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) },
			modifier = Modifier
				.border(
					width = 1.dp,
					color = Color(0xFFCED3DE),
					shape = RoundedCornerShape(size = 10.dp)
				)
				.width(36.dp)
				.height(36.dp)
				.padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
		) {
			Image(painter = painterResource(id = R.drawable.go_back), contentDescription = "")

		}
		Column(
			verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.padding(10.dp)
				.weight(1f)
		) {
			Text(
				monthState.currentMonth.month.name, style = TextStyle(
					fontSize = 20.sp,
					lineHeight = 24.sp,
					fontWeight = FontWeight(600),
					color = Color(0xFF222B45),
				)
			)
			Text(
				monthState.currentMonth.year.toString(), style = TextStyle(
					fontSize = 12.sp,
					lineHeight = 14.4.sp,
					fontWeight = FontWeight(500),
					color = Color(0xFF8F9BB3),
				)
			)
		}
		IconButton(
			onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) },
			modifier = Modifier
				.border(
					width = 1.dp,
					color = Color(0xFFCED3DE),
					shape = RoundedCornerShape(size = 10.dp)
				)
				.width(36.dp)
				.height(36.dp)
				.padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
		) {
			Image(painter = painterResource(id = R.drawable.go_next), contentDescription = "")

		}
	}
}


@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
	Calendar()
}