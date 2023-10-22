package com.sprintsync.ui.components.taskview

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R


@Composable
// param from IBoardviewCategoryItem class
fun TaskviewTitle(taskNavigation: String, taskAssignList: MutableList<Bitmap>?) {
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
	) {
		Row(
			horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
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
				text = taskNavigation, style = TextStyle(
					fontSize = 20.sp,
					lineHeight = 20.sp,
					fontWeight = FontWeight(500),
					color = Color(0xD921005D),
					textAlign = TextAlign.Center,
					letterSpacing = 0.1.sp,
				)
			)
		}
		val resources = LocalContext.current.resources
		val context = LocalContext.current
		// Add ImageBitmap objects to the list
		if (taskAssignList != null) {
			ContextCompat
				.getDrawable(context, R.drawable.nice_avartar)
				?.let {
					taskAssignList.add(it.toBitmap())
				}
		}


		if (taskAssignList != null) {
			Row(horizontalArrangement = Arrangement.spacedBy((-23).dp)) {
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