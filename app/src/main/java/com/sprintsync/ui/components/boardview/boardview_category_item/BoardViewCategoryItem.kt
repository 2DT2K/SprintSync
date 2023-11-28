package com.sprintsync.ui.components.boardview.boardview_category_item


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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.components.TaskTag
import com.sprintsync.ui.theme.spacing


@Composable
fun BoardViewCategoryItem(boardviewItemDetails: TaskResDto) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
    ) {
//        boardviewItemDetails.taskImage?.let {
        Image(
            painterResource(id = R.drawable.nice_avatar),
//                bitmap = it.asImageBitmap(),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
//        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.default),
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.default,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = boardviewItemDetails.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = 0.5.sp
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.largeDefault, Alignment.Start),
                verticalAlignment = Alignment.Top,
                modifier = Modifier.height(28.dp)
            ) {
                boardviewItemDetails.labels?.forEach {
                    TaskTag(tagName = it)
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.small,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check_box),
                        contentDescription = "",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                    Text(
                        text = "SCRUMMER",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
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
                        boardviewItemDetails.point,
                        Modifier
                            .height(24.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(size = 16.dp)
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


//@Preview
//@Composable
//fun BoardViewCategoryItemPreview() {
//    BoardViewCategoryItem(fakeData)
//}