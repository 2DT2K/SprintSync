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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.components.TaskTag
import com.sprintsync.ui.theme.Typography
import com.sprintsync.ui.theme.spacing


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
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(MaterialTheme.spacing.largeDefault)
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
                .padding(MaterialTheme.spacing.largeDefault),

            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = boardviewItemDetails.taskName, style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.largeDefault,
                    Alignment.Start
                ),
                verticalAlignment = Alignment.Top,
                modifier = Modifier.height(MaterialTheme.spacing.extraLarge)
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
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check_box),
                        contentDescription = "",
                        modifier = Modifier
                            .width(MaterialTheme.spacing.large)
                            .height(MaterialTheme.spacing.large)
                    )
                    Text(
                        text = boardviewItemDetails.taskNavigatation, style = Typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.small,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TaskPoint(
                        boardviewItemDetails.taskPoint, Modifier
                            .background(
                                color = Color(0xFFDCCFE3),
                                shape = RoundedCornerShape(size = MaterialTheme.spacing.largeDefault)
                            )
                    )
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