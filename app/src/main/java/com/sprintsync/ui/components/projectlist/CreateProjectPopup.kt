package com.sprintsync.ui.components.projectlist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.data.view_models.MemberViewModel
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectPopup(
    showBottomSheet: Boolean,
    changeSheetState: (Boolean) -> Unit,
    onAddProject: (ProjectDto) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    val memberViewModel = hiltViewModel<MemberViewModel>()
    val memberState by memberViewModel.state.collectAsStateWithLifecycle()
    val uid = memberState.dto?.id
    LaunchedEffect(Unit) {
        memberViewModel.getMe()
    }

    Log.d("log-bug-uid",uid.toString())
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            onDismissRequest = {
                changeSheetState(false)
            },
            sheetState = sheetState,
            windowInsets = WindowInsets(0),
            dragHandle = {
                Drager()
            }
        ) {
            CreateProjectContent(
                onNameChange = { projectName = it },
                onDescriptionChange = { projectDescription = it })

            Spacer(modifier = Modifier.height(24.dp))

            CreateIcon(
                onClick = {
                    if (uid != null) {
                        val project = ProjectDto(
                            id = null,
                            name = projectName,
                            code = projectDescription,
                            manager = uid
                        )

                        onAddProject(project)
                        changeSheetState(false)
                    }
                }
            )

            Spacer(
                Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .windowInsetsPadding(
                        WindowInsets.systemBars.only(WindowInsetsSides.Bottom)
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewCreateProjectPopup() {
    var isOpen by remember { mutableStateOf(true) }
    SprintSyncTheme {
        Button(onClick = { isOpen = true }) {
            Text(text = "click")
        }
//        CreateProjectPopup(isOpen) { state -> isOpen = state }
    }
}