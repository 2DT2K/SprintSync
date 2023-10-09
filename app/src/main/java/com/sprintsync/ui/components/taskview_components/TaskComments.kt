package com.sprintsync.ui.components.taskview_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R


class TaskComments(
    val commenter: String,
    val content: String,
    val commentTime: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskComments(commentList: List<TaskComments>) {
    var comment by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(5.dp)
            .heightIn(max = 500.dp)
//        .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Comments",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xB221005D),
            )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.avataricon),
                contentDescription = "",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
            )

            OutlinedTextField(
                value = comment,
                label = {Text(text ="Add a comment")},
                onValueChange = {
                    comment = it
                }
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xB23F2A65),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .width(32.dp)
                    .height(32.dp)
                    .background(color = Color(0xFFEADDFF), shape = RoundedCornerShape(size = 8.dp))
                    .padding(4.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier.padding(1.dp)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
        ) {
            commentList.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            0.dp,
                            Alignment.CenterHorizontally
                        ),
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avataricon),
                            contentDescription = "image description",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            0.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = it.commenter,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                )
                            )
                            Text(
                                text = it.commentTime,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    lineHeight = 8.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF7B7B7B),
                                )
                            )
                        }
                        Text(
                            text = it.content,
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 14.4.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF7B7B7B),
                                letterSpacing = 0.5.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}

var fakeCmt1 = TaskComments(
    commenter = "Vo Tin Du",
    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. ",
    commentTime = "now",
)
var fakeCmt2 = TaskComments(
    commenter = "Nguyen Hai Dan",
    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. ",
    commentTime = "5 days ago"
)


@Preview(showBackground = true)
@Composable
fun TaskCommentsPreview() {
    TaskComments(commentList = listOf(fakeCmt1, fakeCmt2))
}

