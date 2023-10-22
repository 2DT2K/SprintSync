package com.sprintsync.ui.views.project_view.backlog

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.theme.Green80
import com.sprintsync.ui.theme.Grey60
import com.sprintsync.ui.theme.Purple80
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.Yellow80
import com.sprintsync.ui.view_models.BacklogViewModel

@Composable
fun Backlog(backlogViewModel: BacklogViewModel) {
	val backlogUiState by backlogViewModel.uiState.collectAsState()

	Column(
		modifier = Modifier
			.animateContentSize()
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		CurrentSprintView(backlogUiState.activeSprint)
		Divider()
		IsDoneSprintView(backlogUiState.doneSprints)
	}
}

@Composable
fun CurrentSprintView(currentSprint: BacklogViewModel.Sprint) {
	SprintCard(sprint = currentSprint, isActive = true)
}

@Composable
fun IsDoneSprintView(doneSprints: List<BacklogViewModel.Sprint>) {
	var isOpen by remember {
		mutableStateOf(false)
	}

	Column(
		modifier = Modifier
			.animateContentSize()
			.wrapContentHeight(),
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.clickable {
					isOpen = !isOpen
				}
				.padding(top = 8.dp, bottom = 8.dp)
				.height(40.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			if (isOpen) Image(
				painter = painterResource(id = R.drawable.arrow_down),
				contentDescription = null
			)
			else Image(
				painter = painterResource(id = R.drawable.arrow_up),
				contentDescription = null
			)

			Spacer(modifier = Modifier.width(8.dp))
			Column {
				CustomText(
					text = "IsDone Sprint",
				)
			}

			Spacer(modifier = Modifier.weight(1.0f))

			Icon(
				painter = painterResource(R.drawable.search),
				contentDescription = null,
				modifier = Modifier.clickable(onClick = {})
			)
		}

		if (isOpen) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                items(doneSprints) { sprint ->
//                    SprintCard(sprint = sprint)
//                }
//            }
			doneSprints.forEach() { sprint ->
				SprintCard(sprint = sprint)
			}
		}
	}
}

@Composable
fun SprintCard(sprint: BacklogViewModel.Sprint, isActive: Boolean = false) {
	var isOpen by remember {
		mutableStateOf(false)
	}
//    val enterTransition = remember {
//        expandVertically(
//            expandFrom = Alignment.Top,
//            animationSpec = tween(Ep)
//        ) + fadeIn(
//            initialAlpha = 0.3f,
//            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
//        )
//    }
//    val exitTransition = remember {
//        shrinkVertically(
//            // Expand from the top.
//            shrinkTowards = Alignment.Top,
//            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
//        ) + fadeOut(
//            // Fade in with the initial alpha of 0.3f.
//            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
//        )
//    }

	Column(
		modifier = Modifier
			.animateContentSize()
			.fillMaxWidth()
			.wrapContentHeight()
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.clickable {
					isOpen = !isOpen
				}
				.padding(top = 8.dp, bottom = 8.dp)
				.height(40.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			if (isOpen) Image(
				painter = painterResource(id = R.drawable.arrow_down),
				contentDescription = null
			)
			else Image(
				painter = painterResource(id = R.drawable.arrow_up),
				contentDescription = null
			)

			Spacer(modifier = Modifier.width(8.dp))
			Column {
				CustomText(
					text = sprint.sprintName,
				)
				CustomText(
					text = "Problems: " + sprint.task.size.toString(),
					color = Grey60
				)
			}

			Spacer(modifier = Modifier.weight(1.0f))

			TaskPoint(
				70, Modifier
					.background(
						color = Color(0xFFDCCFE3),
						shape = RoundedCornerShape(size = 10.dp)
					)
			)
			Spacer(modifier = Modifier.width(8.dp))

			TaskPoint(
				70, Modifier
					.background(
						color = Color(0xFFDCCFE3),
						shape = RoundedCornerShape(size = 10.dp)
					)
			)
			Spacer(modifier = Modifier.width(8.dp))

			TaskPoint(
				70, Modifier
					.background(
						color = Color(0xFFDCCFE3),
						shape = RoundedCornerShape(size = 10.dp)
					)
			)
			Spacer(modifier = Modifier.width(8.dp))

			Icon(
				painter = painterResource(R.drawable.more),
				contentDescription = null,
				modifier = Modifier.clickable(onClick = {})
			)
		}

		if (isOpen) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//            ) {
//                items(sprint.task) { task ->
//                    TaskCard(task)
//                }
//            }
			sprint.task.forEach { task ->
				TaskCard(task = task)
			}
			if (isActive) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.height(64.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Image(
						painter = painterResource(id = R.drawable.add),
						contentDescription = "create task icon"
					)
					Spacer(modifier = Modifier.width(8.dp))
					CustomText(text = "Create")
					Spacer(modifier = Modifier.weight(1f))
					Image(
						painter = painterResource(id = R.drawable.attach_file),
						contentDescription = "attach file icon"
					)
				}
			}
		}
	}
}

@Composable
fun TaskCard(task: BacklogViewModel.Task) {
	var status = ""
	var backgroundColor: Color = Color.Transparent
	var icon = 0
	when (task.status) {
		1 -> {
			status = "To Do"
			backgroundColor = Yellow80
			icon = R.drawable.in_progress
		}

		2 -> {
			status = "In Progress"
			backgroundColor = Green80
			icon = R.drawable.production
		}

		3 -> {
			status = "Productivity"
			backgroundColor = Purple80
			icon = R.drawable.production
		}
	}

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(64.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Image(
			modifier = Modifier.size(32.dp),
			painter = painterResource(id = icon),
			contentDescription = "task status icon"
		)
		Column(verticalArrangement = Arrangement.SpaceBetween) {
			CustomText(
				text = task.title,
			)

			Row(
				modifier = Modifier.height(32.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp),
			) {
				CustomText(
					modifier = Modifier.padding(0.dp),
					text = task.name,
					color = Grey60,
				)
				TaskPoint(
					task.point, Modifier
						.background(
							color = Color(0xFFDCCFE3),
							shape = RoundedCornerShape(size = 10.dp)
						)
				)
				Box(
					modifier = Modifier
						.background(
							color = backgroundColor,
							shape = RoundedCornerShape(8.dp)
						)
						.padding(start = 4.dp, end = 4.dp),
					contentAlignment = Alignment.Center
				) {
					CustomText(
						text = status,
						color = Color.White,
						fontSize = 13.sp
					)
				}
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

@Preview(showBackground = true)
@Composable
fun BacklogPreview() {
	val backlogViewModel = BacklogViewModel("")
	SprintSyncTheme {
		Backlog(backlogViewModel)
	}
}

