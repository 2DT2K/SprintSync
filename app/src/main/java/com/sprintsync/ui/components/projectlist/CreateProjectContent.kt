package com.sprintsync.ui.components.projectlist

import android.app.ActivityManager.TaskDescription
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.ExpandableTextField

@Composable
fun CreateProjectContent(onNameChange: (String) -> Unit, onDescriptionChange: (String) -> Unit) {
    Text(
        text = "Project name",
        modifier = Modifier.padding(start = 24.dp),
        fontWeight = FontWeight(600)
    )
    ExpandableTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        value = "",
        label = "",
        placeholder = "Please enter project name",
        onValueChange = {
            onNameChange(it)
        },
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Add a description",
        modifier = Modifier.padding(start = 24.dp),
        fontWeight = FontWeight(600)
    )

    ExpandableTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        value = "",
        label = "",
        placeholder = "Please enter description",
        onValueChange = {
                        onDescriptionChange(it)
        },
    )
}