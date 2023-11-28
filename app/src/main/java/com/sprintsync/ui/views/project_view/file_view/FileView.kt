package com.sprintsync.ui.views.project_view.file_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.view_models.AttachmentViewModel
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.fileview.FileCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing

data class Attachment(
    val id: Int,
    val name: String,
    val content: String,
    val size: String,
    val user: String,
    val fileType: String
)

val fileList = listOf(
    Attachment(
        1, "Document 1",
        "This is the content of Document 1",
        "0.6 MB",
        "khoi nguyen the",
        "pdf"
    ),
    Attachment(
        2,
        "Image 1", "This is the content of Image 1",
        "0.6 MB",
        "khoi nguyen the",
        "jpg"
    ),
    Attachment(
        3,
        "Spreadsheet 1",
        "This is the content of Spreadsheet 1",
        "0.6 MB",
        "khoi nguyen the",
        "xlsx"
    )
)

@Composable
fun FileView(projectId: String? = null) {
    val attachmentVM = hiltViewModel<AttachmentViewModel>()
    val attachmentState by attachmentVM.state.collectAsStateWithLifecycle()
    val attachmentList = attachmentState.dtoList

    LaunchedEffect(Unit) {
        if (projectId != null) {
            attachmentVM.getAttachmentsOfProject(projectId)
        }
    }

    var searchTerm by remember {
        mutableStateOf("")
    }
    Surface {
        Column(
            modifier = Modifier.animateContentSize()
        ) {
            SearchBar(placeHolder = "Search a member", onValueChange = { searchTerm = it })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            LazyColumn(modifier = Modifier.animateContentSize()) {
                if (attachmentList != null) {
                    items(attachmentList) { file ->
                        if (file.name.contains(searchTerm)) FileCard(file)
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FileViewPreview() {
    SprintSyncTheme {
        FileView()
    }
}