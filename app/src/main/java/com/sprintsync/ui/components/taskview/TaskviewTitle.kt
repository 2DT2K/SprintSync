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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.R
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.ui.theme.spacing


@Composable
// param from IBoardviewCategoryItem class
fun TaskviewTitle(taskNavigation: String, taskAssignor: MemberDto) {
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
//        val resources = LocalContext.current.resources
//        val context = LocalContext.current
        // Add ImageBitmap objects to the list

        Image(painter = painterResource(id = R.drawable.avataricon), contentDescription = "")
    }
}


@Preview(showBackground = true)
@Composable
fun TaskviewTitlePreview() {
    TaskviewTitle(taskNavigation = "SCURMMER-5", taskAssignor = MemberDto("", "", "", "", ""))
}