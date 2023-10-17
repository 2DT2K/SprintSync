package com.sprintsync.ui.components.boardview.boardview_category_item


import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.components.TaskTag


class IBoardviewCategoryItem(
    var taskName: String,
    var taskTag: List<String>,
    var taskNavigatation: String,
    var taskImage: Bitmap?,
    var taskPoint: Number,
    var taskAssignList: List<Bitmap>?,
)

@Composable
fun BoardViewCategoryItem(boardviewItemDetails: IBoardviewCategoryItem) {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
    ) {
        boardviewItemDetails.taskImage?.let {
            androidx.compose.foundation.Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .background(
//                    color = Color(0xFFFFFFFF),
//                    shape = RoundedCornerShape(size = 10.dp)
//                )
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),

            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = boardviewItemDetails.taskName, style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF21005D),
                    letterSpacing = 0.5.sp
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
                modifier = Modifier.height(32.dp)
            ) {
                boardviewItemDetails.taskTag.forEach { TaskTag(tagName = it) }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        0.dp,
                        Alignment.CenterHorizontally
                    ), verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(3.dp, bottom = 3.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check_box),
                        contentDescription = "",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                    Text(
                        text = boardviewItemDetails.taskNavigatation, style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xD95E4E79),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.1.sp,
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        5.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TaskPoint(boardviewItemDetails.taskPoint, Modifier
                        .background(
                            color = Color(0xFFDCCFE3),
                            shape = RoundedCornerShape(size = 10.dp)
                        ))
                    Box {
//                        if (boardviewItemDetails?.taskAssignList != null) {
//                            for (image in boardviewItemDetails.taskAssignList!!) {
//                                Image(
//                                    bitmap = image,
//                                    contentDescription = "",
//                                    modifier = Modifier
//                                        .align(Alignment.Center)
//                                        .size(32.dp)
//                                        .padding(10.dp)
//                                )
//                            }
//                        }
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = ""
                        )

                    }
                }

            }
        }
    }
}

var fakeData: IBoardviewCategoryItem = IBoardviewCategoryItem(
    "Code Homepage",
    listOf("Homepage", "FE"),
    "Scrummer123",
    null,
    90,
    null,
)

@Preview
@Composable
fun BoardViewCategoryItemPreview() {
    BoardViewCategoryItem(fakeData)
}