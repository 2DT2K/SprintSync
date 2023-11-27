package com.sprintsync.ui.components.taskview

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.data.dtos.response.CommentResDto
import com.sprintsync.ui.components.Comment
import com.sprintsync.ui.components.CommentTextField
import com.sprintsync.ui.theme.spacing


data class TaskComments(
    val commenter: String,
    val content: String,
    val commentTime: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskComments(commentList: List<CommentResDto>?) {
    var comment by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.small,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .heightIn(max = 500.dp)
//        .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Comments",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.default,
                Alignment.Start
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.avataricon),
                contentDescription = "",
                modifier = Modifier
                    .width(MaterialTheme.spacing.extraLarge)
                    .height(MaterialTheme.spacing.extraLarge)
            )
            CommentTextField(
                placeholder = "Add a comment",
                value = comment,
                onValueChange = {
                    comment = it
                },
                modifier = Modifier.weight(1f)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(size = MaterialTheme.spacing.default)
                    )
                    .width(MaterialTheme.spacing.extraLarge)
                    .height(MaterialTheme.spacing.extraLarge)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(size = MaterialTheme.spacing.default)
                    )
                    .padding(MaterialTheme.spacing.small)

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
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            commentList?.forEach {
                Comment(
                    commenter = it.commenter.name,
                    commentTime = it.createdAt,
                    content = it.content
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TaskCommentsPreview() {

}

