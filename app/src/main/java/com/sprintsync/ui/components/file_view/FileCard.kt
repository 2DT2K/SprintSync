package com.sprintsync.ui.components.file_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.Grey60
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.views.project_view.file_view.Attachment
import com.sprintsync.ui.views.project_view.file_view.FileView


@Composable
fun FileCard(
	file: Attachment
) {
	// TODO: add more file type to icon
	val icon: Int = when (file.fileType) {
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
				Text(text = file.name)
				Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
					Box(modifier = Modifier.width(48.dp)) {
						Text(
							text = file.user,
							overflow = TextOverflow.Ellipsis,
							color = Grey60,
							fontWeight = FontWeight(500)
						)
					}
					Text(
						text = file.size,
						color = Purple20,
						fontWeight = FontWeight(500)
					)
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
