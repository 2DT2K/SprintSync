package com.sprintsync.ui.components.fileview

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.sprintsync.R
import com.sprintsync.ui.components.backlog.TaskComposer

@Composable
fun AddFileFAB() {
    val (showBottomSheet,setShowBottomSheet) = remember{ mutableStateOf(false) }

    FloatingActionButton(
        onClick = {
            setShowBottomSheet(true)
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Add",
        )
    }
    TaskComposer(showBottomSheet = showBottomSheet, changeVisibility = setShowBottomSheet)
}