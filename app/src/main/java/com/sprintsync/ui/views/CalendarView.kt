package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.Calendar


@Composable
fun CalendarView() {
	Column(
		verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
		horizontalAlignment = Alignment.Start,
	) {
		Calendar()
	}
}

@Preview(showBackground = true)
@Composable
fun CalendarViewPreview() {
	CalendarView()
}