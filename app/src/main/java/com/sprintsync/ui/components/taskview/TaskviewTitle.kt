package com.sprintsync.ui.components.taskview

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.ui.theme.spacing


@Composable
// param from IBoardviewCategoryItem class
fun TaskviewTitle(taskNavigation: String, taskAssignList: MutableList<Bitmap>?) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
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
                    .width(MaterialTheme.spacing.large)
                    .height(MaterialTheme.spacing.large)
            )
            Text(
                text = taskNavigation, style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
        val resources = LocalContext.current.resources
        val context = LocalContext.current
        // Add ImageBitmap objects to the list
        if (taskAssignList != null) {
            ContextCompat
                .getDrawable(context, R.drawable.nice_avatar)
                ?.let {
                    taskAssignList.add(it.toBitmap())
                }
        }


        if (taskAssignList != null) {
            Row(horizontalArrangement = Arrangement.spacedBy(-MaterialTheme.spacing.large)) {
                taskAssignList.forEachIndexed { index, image ->
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .zIndex(index.toFloat())
                            .width(46.dp)
                            .height(32.dp)
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskviewTitlePreview() {
    TaskviewTitle(taskNavigation = "SCURMMER-5", taskAssignList = mutableListOf())
}