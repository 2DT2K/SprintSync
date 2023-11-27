package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing

@Composable
fun ChartTitle(
    sprintName: String,
    onSprintNameChange: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var sprintList by remember {
        mutableStateOf({})
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Title",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Box {
            OutlinedButton(
                onClick = {
                    expanded = !expanded
                },
                contentPadding = PaddingValues(
                    MaterialTheme.spacing.default,
                    MaterialTheme.spacing.small,
                    MaterialTheme.spacing.default,
                    MaterialTheme.spacing.small
                ),
            ) {
                Text(
                    text = sprintName,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Icon(
                    painter = painterResource(id = R.drawable.mini_down_arrow),
                    contentDescription = "image description",
                    modifier = Modifier
                        .width(MaterialTheme.spacing.medium)
                        .height(MaterialTheme.spacing.medium)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.clickable { expanded = !expanded }
            ) {
                listOf("Sprint 1", "Sprint 2", "Sprint 3").forEach { sprint ->
                    TextButton(
                        onClick = {
                            onSprintNameChange(sprint)
                            expanded = false
                        }
                    ) {
                        Text(
                            text = sprint,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartTitlePreview() {
    ChartTitle(sprintName = "LMAO1234", onSprintNameChange = {})
}