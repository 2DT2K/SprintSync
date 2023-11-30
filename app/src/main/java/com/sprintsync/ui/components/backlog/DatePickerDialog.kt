package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.Grey80
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun convertLongToLocalDateTime(epochMilli: Long?): LocalDateTime {
    if (epochMilli == null) return LocalDateTime.now()
    val instant = epochMilli?.let { Instant.ofEpochMilli(it) }
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(onValueChange: (String) -> Unit = {}) {
    var isOpen by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }
    val deadline = convertLongToLocalDateTime(datePickerState.selectedDateMillis)

    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {}
                ),
            value = deadline.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            ),
            onValueChange = { },
            trailingIcon = {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp),
                        painter = painterResource(
                            id = R.drawable.calendar
                        ),
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Grey80
            ),
            textStyle = MaterialTheme.typography.bodyLarge
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable { isOpen = !isOpen },
        )
    }

    if (isOpen) {
        androidx.compose.material3.DatePickerDialog(
            onDismissRequest = {
                isOpen = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isOpen = false
                        onValueChange(deadline.toString())
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isOpen = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

