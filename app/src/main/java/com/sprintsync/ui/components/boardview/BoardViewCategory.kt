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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
            MaterialTheme.spacing.largeDefault,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth(0.96f)
            .background(
                color = Color(0xFFEADDFF),
                shape = RoundedCornerShape(size = MaterialTheme.spacing.largeDefault)
            )
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .height(MaterialTheme.spacing.large)
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
                    text = boardviewCategory.categoryName, style = Typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = boardviewCategory.numberOfTask.toString(), style = Typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.width(MaterialTheme.spacing.large)) {
                Image(painter = painterResource(id = R.drawable.plus_icon), contentDescription = "")
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