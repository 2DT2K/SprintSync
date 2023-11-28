package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing

@Composable
fun IssueItem(
    issueType: String,
    issueDescription: String,
    issueTimeLine: String,
    onClick: () -> Unit = {}
) {
    val issueTypePicModifier = Modifier
        .size(32.dp)
        .background(
            color = when (issueType) {
                "Task"    -> MaterialTheme.colorScheme.secondary
                "Bug"     -> MaterialTheme.colorScheme.errorContainer
                "Project" -> MaterialTheme.colorScheme.tertiary
                else      -> Color(0xFF000000)
            },
            shape = RoundedCornerShape(size = 8.dp)
        )
        .padding(MaterialTheme.spacing.small)
    Surface(color = Color.Unspecified, onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.default)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = issueTypePicModifier
            ) {
                when (issueType) {
                    "Task"    -> Icon(
                        painter = painterResource(id = R.drawable.check_circle),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    "Bug"     -> Icon(
                        painter = painterResource(id = R.drawable.bug_report),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )

                    "Project" -> Icon(
                        painter = painterResource(id = R.drawable.folder_fill),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )

                    else      -> {

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = issueType,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = issueTimeLine,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(
                        text = issueDescription,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IssueItemPreview() {
    IssueItem("Task", "Design UI for HomePage", "now")
}


