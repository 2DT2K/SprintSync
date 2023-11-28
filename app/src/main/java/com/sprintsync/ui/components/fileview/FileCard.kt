package com.sprintsync.ui.components.fileview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.sprintsync.R
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.views.project_view.file_view.FileView


@Composable
fun FileCard(
    file: AttachmentDto
) {
    // TODO: add more file type to icon
    val icon: Int = when (file.fileType) {
        "pdf" -> R.drawable.pdf
        "doc" -> R.drawable.doc
        "xlsx" -> R.drawable.xlsx
        "png", "jpeg" -> R.drawable.picture
        else -> {
            R.drawable.doc
        }
    }

    Surface {
        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.default,
                    bottom = MaterialTheme.spacing.default,
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "file type icon",
                tint = Color.Unspecified
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.small,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = file.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.default
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${file.fileSize}MB",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Box(modifier = Modifier.weight(1f)) {
                        Text(
                            text = file.createdAt,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
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
