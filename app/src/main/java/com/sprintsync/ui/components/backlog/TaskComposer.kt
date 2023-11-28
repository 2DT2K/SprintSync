package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomModalBottomSheet
import com.sprintsync.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskComposer(showBottomSheet: Boolean, changeVisibility: (Boolean) -> Unit) {

    Box(modifier = Modifier) {
        CustomModalBottomSheet(
            isSheetShown = showBottomSheet,
            changeVisibility = { changeVisibility(it) }) {
            Column(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
            ) {
                Text(
                    text = "Create issue with attachment",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .clickable { }
                        .padding(
                            MaterialTheme.spacing.default,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "take photo",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(text = "Take photo")
                }

                Row(
                    modifier = Modifier
                        .clickable { }
                        .padding(
                            MaterialTheme.spacing.default,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.videocam),
                        contentDescription = "Record video",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(text = "Record video")
                }

                Row(
                    modifier = Modifier
                        .clickable { }
                        .padding(
                            MaterialTheme.spacing.default,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(0.dp)
                            .width(24.dp)
                            .scale(1.5f),
                        painter = painterResource(id = R.drawable.attach_file_icon),
                        contentDescription = "Choose file",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(text = "Choose file")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTest() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "take photo"
            )

            Text(text = "Take photo")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.videocam),
                contentDescription = "Record video"
            )

            Text(text = "Record video")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .padding(0.dp)
                    .width(24.dp)
                    .scale(1.5f),
                painter = painterResource(id = R.drawable.attach_file_icon),
                contentDescription = "Choose file"
            )

            Text(text = "Choose file")
        }

        Spacer(
            Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        )

        Spacer(
            Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        )
    }
}