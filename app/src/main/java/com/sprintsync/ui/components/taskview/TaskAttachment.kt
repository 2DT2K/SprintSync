package com.sprintsync.ui.components.taskview

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

class Attachment(
    val name: String,
    val fileType: String,
    val fileSize: Double,
)

@Composable
fun TaskAttachment(attachmentList: List<Attachment>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
    ) {
        Text(
            text = "Attachment",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xB221005D),
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth().horizontalScroll(rememberScrollState())
            ,
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                attachmentList.forEach {
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
                        color = Color(0xFFC7BBDA),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .background(color = Color(0xFFEDE2FF), shape = RoundedCornerShape(size = 8.dp))
                    .padding(4.dp),
//                    .drawBehind {
//                        drawRoundRect(color = Color.Red, style = Style.)
//                    },
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Image(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(24.dp)
                            .height(24.dp)
                            .background(color = Color(0xFFEDE2FF)),
                        painter = painterResource(id = R.drawable.upload_file),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

var firstAttachment = Attachment("Test", "pdf", 6.8)
var secondAttachment = Attachment("Anhdepquadithoilmao1234", "png", 0.6)
var attachmentList = listOf<Attachment>(firstAttachment, secondAttachment)

@Preview(showBackground = true)
@Composable
fun TaskAttachmentPreview() {
    TaskAttachment(attachmentList = attachmentList)
}
