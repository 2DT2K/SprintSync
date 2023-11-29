package com.sprintsync.ui.components.taskview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeTaskStateButton(
    taskState: String,
    updateState: (TaskDto) -> Unit,
    statusList: List<String>,
    taskDetails:TaskResDto?,
) {
    var taskStateTest by remember {
        mutableStateOf(taskState)
    }
    val currentTaskDetails = taskDetails?.toDto()

    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(
            onClick = { expanded = !expanded },
            contentPadding = PaddingValues(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.smallMedium
            ),
            shape = RoundedCornerShape(MaterialTheme.spacing.largeDefault),
            modifier = Modifier.defaultMinSize(minHeight = 46.dp),
            colors = buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "image description",
                contentScale = ContentScale.None,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(
                text = taskStateTest,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentWidth()
        ) {
            statusList.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        taskStateTest = it
                        expanded = false
                        if (currentTaskDetails != null) {
                            currentTaskDetails.statusIndex = statusList.indexOf(it)
                            updateState(currentTaskDetails)
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeStatePreview() {
//    ChangeTaskStateButton(taskState = "IN PROGRESS")
}
