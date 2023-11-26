package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.data.view_models.BacklogViewModel
import com.sprintsync.ui.components.StoryPoint
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.theme.Green80
import com.sprintsync.ui.theme.Grey60
import com.sprintsync.ui.theme.InProgressStatus
import com.sprintsync.ui.theme.ProductivityStatus
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.Purple80
import com.sprintsync.ui.theme.ToDoStatus
import com.sprintsync.ui.theme.Yellow80

@Composable
fun TaskCard(task: BacklogViewModel.Task) {
	var status = ""
	var backgroundColor: Color = Color.Transparent
	var icon = 0
	when (task.status) {
		1 -> {
			status = "To Do"
			backgroundColor = ToDoStatus
			icon = R.drawable.in_progress
		}

		2 -> {
			status = "In Progress"
			backgroundColor = InProgressStatus
			icon = R.drawable.production
		}

		3 -> {
			status = "Productivity"
			backgroundColor = ProductivityStatus
			icon = R.drawable.production
		}
	}

	Row(
		modifier = Modifier
			.clickable { }
			.fillMaxWidth()
			.height(64.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Image(
			modifier = Modifier.size(28.dp),
			painter = painterResource(icon),
			contentDescription = null,
		)
		Column(verticalArrangement = Arrangement.SpaceBetween) {
			Text(
				text = task.title,
				color = Purple20,
				fontWeight = FontWeight(400),
			)

			Row(
				modifier = Modifier.height(32.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp),
			) {
				Text(
					modifier = Modifier.padding(0.dp),
					text = task.name,
					color = Grey60,
					fontWeight = FontWeight(500)
				)

				StoryPoint(point = task.point)

				TaskProcess(
					title = status, color = backgroundColor
						.toArgb()
						.toLong()
				)
			}
		}
		Spacer(modifier = Modifier.weight(1.0f))
		ContextCompat
			.getDrawable(LocalContext.current, R.drawable.nice_avatar)
			?.let { it1 ->
				task.assignees = mutableListOf(it1.toBitmap(), it1.toBitmap())
			}
		Row(
			horizontalArrangement = Arrangement.spacedBy((-14).dp, Alignment.End),
			verticalAlignment = Alignment.CenterVertically,
		) {
			task.assignees.forEachIndexed { index, image ->
				Image(
					bitmap = image.asImageBitmap(),
					contentDescription = "",
					modifier = Modifier
						.zIndex(index.toFloat())
						.width(24.dp)
						.height(24.dp)
				)
			}
		}
	}
}
