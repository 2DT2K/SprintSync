package com.sprintsync.ui.components.taskview


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.ui.theme.spacing

@Composable
fun TaskDescription(taskDescription: String?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = "Description", style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        if (taskDescription != null) {
            Text(
                text = taskDescription, style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDescriptionPreview() {
    TaskDescription(
        taskDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aenean lorem sapien, lacinia ut odio a, dictum scelerisque est. Fusce mattis ac eros ut efficitur. Integer vel magna suscipit, tincidunt risus commodo, viverra est. "
    )
}