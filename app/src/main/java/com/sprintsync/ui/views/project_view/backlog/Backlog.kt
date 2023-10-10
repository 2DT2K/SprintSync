package com.sprintsync.ui.views.project_view.backlog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun Backlog() {
    Column() {
        CurrentSprintView()
        IsDoneSprintView()
    }
}

@Composable
fun CurrentSprintView() {
    Column {
        Row(
            modifier = Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    radius = 250.dp,
                ),
                onClick = {})
        ) {

        }
    }
}

@Composable
fun IsDoneSprintView() {

}

@Preview
@Composable
fun BacklogPreview() {
    SprintSyncTheme {
        Backlog()
    }
}

