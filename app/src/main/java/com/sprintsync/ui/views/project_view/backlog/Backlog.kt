package com.sprintsync.ui.views.project_view.backlog

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.R
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.view_models.BacklogViewModel
import com.sprintsync.data.view_models.SprintViewModel
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.components.backlog.SprintCard
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.SprintSyncTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun Backlog(projectID: String?) {
    val sprintVM = hiltViewModel<SprintViewModel>()
    val sprintState by sprintVM.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (projectID != null) {
            sprintVM.getSprintsOfProject(projectID)
        }
    }

    Column(
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //TODO: wait for active sprint api
        CurrentSprintView(sprintState.dtoList)
        IsDoneSprintView(sprintState.dtoList)
    }
}

@Composable
fun CurrentSprintView(currentSprint: List<SprintDto>?) {
    currentSprint?.forEach() { sprint ->
        SprintCard(sprint = sprint, isActive = true)
    }.let {
        if (it != null) Divider()
    }
}

@Composable
fun IsDoneSprintView(doneSprints: List<SprintDto>?) {
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
            Icon(
                painter = painterResource(
                    id = if (isOpen) R.drawable.arrow_down_2
                    else R.drawable.arrow_up
                ),
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp),
                tint = Grey40,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "IsDone Sprint",
                )
            }

            Spacer(modifier = Modifier.weight(1.0f))

            Icon(
                painter = painterResource(R.drawable.more),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {})
            )
        }

        AnimatedVisibility(visible = isOpen) {
            Column {
                doneSprints?.forEach() { sprint ->
                    SprintCard(sprint = sprint)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BacklogPreview() {
    val backlogViewModel = BacklogViewModel("")
    SprintSyncTheme {

    }
}

