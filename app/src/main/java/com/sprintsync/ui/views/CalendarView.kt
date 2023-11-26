package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sprintsync.ui.components.Calendar
import com.sprintsync.ui.theme.spacing


@Composable
fun CalendarView(navController: NavController? = null) {
    Column(horizontalAlignment = Alignment.Start) {
        Calendar()
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarViewPreview() {
    CalendarView()
}