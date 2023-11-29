package com.sprintsync.ui.components.fileview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.data.dtos.response.AttachmentResDto
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.views.project_view.file_view.FileView
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun FileCard(
    file: AttachmentResDto
) {
    // TODO: add more file type to icon
    val icon: Int = when (file.fileType) {
        "pdf"                -> R.drawable.pdf
        "doc"                -> R.drawable.doc
        "xlsx"               -> R.drawable.xlsx
        "png", "jpeg", "jgp" -> R.drawable.picture
        else                 -> {
            R.drawable.doc
        }
    }
    val dlDate = LocalDateTime.parse(file.createdAt)
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.HALF_DOWN

    Surface {
        Row(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.default,
                    bottom = MaterialTheme.spacing.default,
                )
                .fillMaxWidth()
                 .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "file type icon",
            )
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.small,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier.widthIn(max = 200.dp),
                    text = file.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.height(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.default
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${df.format(file.fileSize*0.1/(1000*1000))}MB",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = dlDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
