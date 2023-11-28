package com.sprintsync.ui.components.projectlist

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.sprintsync.R

@Composable
fun AddProjectFAB() {
    var isOpen by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = {
            isOpen = true
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Add",
        )
    }
    CreateProjectPopup(isOpen) { state -> isOpen = state }
}