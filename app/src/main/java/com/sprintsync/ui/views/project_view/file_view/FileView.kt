package com.sprintsync.ui.views.project_view.file_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.file_view.FileCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.views.project_view.GridItem
import com.sprintsync.ui.views.project_view.member.RoleColor

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
fun FileView() {
    var searchTerm by remember {
        mutableStateOf("")
    }
    Surface {
        Column(
            modifier = Modifier.animateContentSize()
        ) {
            SearchBar(placeHolder = "Search a member", onValueChange = { searchTerm = it })
            LazyColumn(modifier = Modifier.animateContentSize()) {
                items(fileList) { file ->
                    if (file.name.contains(searchTerm)) FileCard(file)
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