package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomModalBottomSheet
import com.sprintsync.ui.components.CustomText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskComposer() {
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        Image(
            modifier = Modifier.clickable { showBottomSheet = true },
            painter = painterResource(id = R.drawable.attach_file_icon),
            contentDescription = "attach file icon"
        )

        CustomModalBottomSheet(
            isSheetShown = showBottomSheet,
            changeVisibility = { showBottomSheet = it }) {
            Column(
                modifier = Modifier.padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomText(text = "Create issue with attachment")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "take photo"
                    )

                    CustomText(text = "Take photo")
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.videocam),
                        contentDescription = "Record video"
                    )

                    CustomText(text = "Record video")
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

                    CustomText(text = "Choose file")
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

            CustomText(text = "Take photo")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.videocam),
                contentDescription = "Record video"
            )

            CustomText(text = "Record video")
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

            CustomText(text = "Choose file")
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