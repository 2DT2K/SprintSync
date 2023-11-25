package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.ui.theme.spacing

@Composable
fun ChartInfor(remaining: Int, completed: Int, remainingColor: Color, completedColor: Color) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        WorkItem("Remaining work", remaining.toString(), remainingColor)
        WorkItem("Completed work", completed.toString(), completedColor)
    }
}

@Composable
fun WorkItem(title: String, count: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.smallMedium,
            Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(MaterialTheme.spacing.small)
                .height(MaterialTheme.spacing.mediumLarge)
                .background(color = color, shape = RoundedCornerShape(MaterialTheme.spacing.small))
        ) {
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = count,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartInforPreView() {
    ChartInfor(
        remaining = 4,
        completed = 6,
        remainingColor = Color.Cyan,
        completedColor = Color.Yellow
    )
}