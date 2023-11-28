package com.sprintsync.ui.components.taskview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing

@Composable
fun SingleFileAttachment(fileName: String, fileType: String, fileSize: Long) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(size = MaterialTheme.spacing.default)
            )
            .padding(
                start = MaterialTheme.spacing.default,
                MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.smallMedium,
                bottom = MaterialTheme.spacing.small
            )
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            when (fileType) {
                "pdf" -> Image(
                    painter = painterResource(id = R.drawable.pdf_icon),
                    contentDescription = "",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(MaterialTheme.spacing.extraLarge)
                        .height(MaterialTheme.spacing.extraLarge)
                )

                "png" -> Image(
                    painter = painterResource(id = R.drawable.png_icon),
                    contentDescription = "",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .width(MaterialTheme.spacing.extraLarge)
                        .height(MaterialTheme.spacing.extraLarge)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = fileName,
                    modifier = Modifier.widthIn(max = 45.dp),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = ".$fileType",
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Text(
                text = fileSize.toString() + "MB", style = TextStyle(
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF7B7B7B),
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.largeDefault,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(MaterialTheme.spacing.mediumLarge)
                .height(MaterialTheme.spacing.mediumLarge)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = MaterialTheme.spacing.default)
                )
                .padding(MaterialTheme.spacing.small)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.cancel_icon),
                    contentDescription = ""
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SingleFileAttachmentPreview() {
    SingleFileAttachment(fileName = "Test", fileType = "pdf", fileSize = 6)
}