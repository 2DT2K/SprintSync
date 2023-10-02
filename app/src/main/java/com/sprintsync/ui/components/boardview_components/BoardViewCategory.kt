package com.sprintsync.ui.components.boardview_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sprintsync.ui.components.boardview_components.boardview_category_item.BoardViewCategoryItem
import com.sprintsync.ui.components.boardview_components.boardview_category_item.IBoardviewCategoryItem


class IBoardViewCategory(
    var categoryName: String,
    var numberOfTask: Number,
    var taskList: List<IBoardviewCategoryItem>?
)

@Composable
fun BoardViewCategory(boardviewCategory: IBoardViewCategory) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFEADDFF), shape = RoundedCornerShape(size = 10.dp))
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = boardviewCategory.categoryName, style = TextStyle(
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF21005D),
                        letterSpacing = 0.5.sp,
                    )
                )
                Text(
                    text = boardviewCategory.numberOfTask.toString(), style = TextStyle(
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF21005D),
                        letterSpacing = 0.5.sp,
                    )
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
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