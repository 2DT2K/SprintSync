package com.sprintsync.ui.components.taskview_components


import android.app.ActivityManager.TaskDescription
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskDescription(taskDescription: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
    ) {
        Text(
            text = "Description", style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xB221005D),
            )
        )
        Text(
            text = taskDescription, style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 16.8.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF641677),
                letterSpacing = 0.1.sp,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDescriptionPreview() {
    TaskDescription(taskDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aenean lorem sapien, lacinia ut odio a, dictum scelerisque est. Fusce mattis ac eros ut efficitur. Integer vel magna suscipit, tincidunt risus commodo, viverra est. ")
}