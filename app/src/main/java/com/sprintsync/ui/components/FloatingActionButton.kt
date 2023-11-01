package com.sprintsync.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun CustomFloatingActionButton() {
    FloatingActionButton(onClick = { }) {
        Icon(Icons.Default.Add, contentDescription = "Create new project")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewFloatingActionButton() {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }
    SprintSyncTheme {
        Scaffold(floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {

            }
        }) { innerPadding ->
            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.heightIn(min = 200.dp, max = 1000.dp).padding(innerPadding),
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Column {
                        CustomText(text = "Project name")
                        ExpandTextField(
                            label = "",
                            placeholder = "Add a description",
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "create project"
                        )
                    }
                    Column {
                        CustomText(text = "Project name")
                        ExpandTextField(
                            label = "",
                            placeholder = "Add a description",
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "create project"
                        )
                    }
                    Column {
                        CustomText(text = "Project name")
                        ExpandTextField(
                            label = "",
                            placeholder = "Add a description",
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "create project"
                        )
                    }
                }
            }
        }
    }
}