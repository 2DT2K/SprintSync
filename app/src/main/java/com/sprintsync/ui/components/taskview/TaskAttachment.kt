package com.sprintsync.ui.components.taskview

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.views.fakeTask

class Attachment(
    val name: String,
    val fileType: String,
    val fileSize: Double,
)
@Composable
fun TaskAttachment(attachmentList: List<AttachmentDto>?) {
    val context = LocalContext.current
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult =
        {
            val item = it?.let { it1 -> context.contentResolver.openInputStream(it1) }
            val bytes = item?.readBytes()
            println(bytes)
            bytes?.let { byteArr -> println(String(byteArr)) }
            item?.close()
        }
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = "Attachment",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small,
                Alignment.Start
            ),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.small,
                    Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                attachmentList?.forEach {
                    SingleFileAttachment(
                        fileName = it.name,
                        fileType = it.fileType,
                        fileSize = it.fileSize
                    )
                }
            }
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(MaterialTheme.spacing.default)
                    )
                    .background(
                        color = Color(0xFFEDE2FF),
                        shape = RoundedCornerShape(MaterialTheme.spacing.default)
                    )
                    .padding(MaterialTheme.spacing.small),
//                    .drawBehind {
//                        drawRoundRect(color = Color.Red, style = Style.)
//                    },
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.largeDefault,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                IconButton(
                    onClick = { filePickerLauncher.launch("*/*") }
                ) {
                    Image(
                        modifier = Modifier
                            .width(MaterialTheme.spacing.large)
                            .height(MaterialTheme.spacing.large)
                            .background(color = Color(0xFFEDE2FF)),
                        painter = painterResource(id = R.drawable.upload_file),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskAttachmentPreview() {
    TaskAttachment(attachmentList = fakeTask.attachments)
}
