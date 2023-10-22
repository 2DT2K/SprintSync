package com.sprintsync.ui.views.project_view.file_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.theme.SprintSyncTheme

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

@Composable
fun FileCard(
	file: Attachment
) {
	// TODO: add more file type to icon
	var icon: Int = when (file.fileType) {
		"pdf"  -> R.drawable.pdf
		"xlsx" -> R.drawable.pdf
		else   -> {
			R.drawable.picture
		}
	}

	Surface {
		Row(
			modifier = Modifier
				.padding(top = 8.dp, bottom = 8.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				painter = painterResource(id = icon),
				contentDescription = "file type icon",
				tint = Color.Unspecified
			)
			Column(
				verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
				horizontalAlignment = Alignment.Start,
			) {
				CustomText(text = file.name)
				Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
					Box(modifier = Modifier.width(160.dp)) {
						CustomText(text = file.user, overflow = TextOverflow.Ellipsis)
					}
					CustomText(text = file.size)
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