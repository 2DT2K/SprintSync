package com.sprintsync.ui.components.boardview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.sprintsync.ui.components.boardview.boardview_category_item.BoardViewCategoryItem
import com.sprintsync.ui.components.boardview.boardview_category_item.IBoardviewCategoryItem
import com.sprintsync.ui.theme.Typography
import com.sprintsync.ui.theme.spacing


class IBoardViewCategory(
    var categoryName: String,
    var numberOfTask: Number,
    var taskList: List<IBoardviewCategoryItem>?
)

@Composable
fun BoardViewCategory(boardviewCategory: IBoardViewCategory) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth(0.96f)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
//                Need to add TextStyle into theme/Type
                Text(
                    text = boardviewCategory.categoryName,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    letterSpacing = 0.5.sp,
                )
                Text(
                    text = boardviewCategory.numberOfTask.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    letterSpacing = 0.5.sp,
                )
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.width(24.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        boardviewCategory.taskList?.forEach {
            BoardViewCategoryItem(boardviewItemDetails = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardViewCategoryPreview() {
    BoardViewCategory(fakedata)
}