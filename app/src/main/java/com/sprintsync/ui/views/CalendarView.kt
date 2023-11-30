package com.sprintsync.ui.views


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.sprintsync.ui.components.Calendar


@Composable
fun CalendarView(navController: NavController? = null, statusList:List<String>?) {
    Column(horizontalAlignment = Alignment.Start) {
        Calendar(statusList)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CalendarViewPreview() {
//    CalendarView()
//}