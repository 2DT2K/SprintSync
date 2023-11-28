package com.sprintsync.ui.components

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.components.calendar.CalendarTaskview
import com.sprintsync.ui.theme.spacing
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
import java.time.YearMonth
import java.time.format.TextStyle.FULL
import java.util.Locale


@Composable
fun Calendar(statusList: List<String>?) {
    val taskViewVM = hiltViewModel<TaskViewModel>()
    val tasksState by taskViewVM.state.collectAsStateWithLifecycle()

    val allTask = tasksState.dtoList
    LaunchedEffect(Unit) {
        taskViewVM.getMyTasks()
    }


    val (currentDaySelected, setCurrentDaySelected) = remember {
        mutableStateOf(LocalDateTime.now())
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.Start,
    ) {
        SelectableCalendar(
            modifier = Modifier.animateContentSize(),
            showAdjacentMonths = true,
            firstDayOfWeek = MONDAY,
            dayContent = {
                DayContent(
                    dayState = it,
                    setCurrentDaySelected = setCurrentDaySelected,
                    allTask = allTask,
                    statusList = statusList,
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
            allTask?.forEach {
                val dlDate = LocalDateTime.parse(it.deadline)
                if (currentDaySelected.month == dlDate.month
                    && currentDaySelected.dayOfMonth == dlDate.dayOfMonth
                    && currentDaySelected.year == dlDate.year
                ) {
                    CalendarTaskview(calendarTask = it, statusList = statusList)
                }
            }
        }

    }

}

@SuppressLint("DiscouragedApi")
@Composable
private fun DayContent(
    dayState: DayState<DynamicSelectionState>,
    setCurrentDaySelected: (LocalDateTime) -> Unit,
    allTask: List<TaskResDto>?,
    statusList: List<String>?
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


    allTask?.forEach {
        val dlDate = LocalDateTime.parse(it.deadline)
        if (date.month == dlDate.month
            && date.dayOfMonth == dlDate.dayOfMonth
            && date.year == dlDate.year
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
                text = dayState.date.dayOfMonth.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = Color(color),
                textAlign = TextAlign.Center,
                modifier = Modifier
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
                val usedStatusList = mutableListOf<String>()
                allTask?.forEach {
                    val dlDate = LocalDateTime.parse(it.deadline)
                    if (date.month == dlDate.month
                        && date.dayOfMonth == dlDate.dayOfMonth
                        && date.year == dlDate.year
                    ) {
                        if (!usedStatusList.contains(statusList?.get(it.statusIndex))) {
                            statusList?.get(it.statusIndex)?.let { it1 -> usedStatusList.add(it1) }
                            var dotColor = "blue_dot"
                            when (statusList?.get(it.statusIndex)) {
                                "To Do" -> dotColor = "blue_dot"
                                "In Progress" -> dotColor = "yellow_dot"
//                            "Stuck" -> dotColor = "red_dot"
//                            "Review" -> dotColor = "blue_dot"
                                "Done" -> dotColor = "green_dot"
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
}

@Composable
private fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
    Row {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
        horizontalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.default)
    ) {
        IconButton(
            onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) },
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .size(36.dp)
                .padding(2.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.go_back), contentDescription = "")

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                0.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .weight(1f)
        ) {
            Text(
                monthState.currentMonth.month.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                monthState.currentMonth.year.toString(),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        IconButton(
            onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) },
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .size(36.dp)
                .padding(2.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.go_next), contentDescription = "")

        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    Calendar(listOf("To Do", "In Progress", "Done"))
}