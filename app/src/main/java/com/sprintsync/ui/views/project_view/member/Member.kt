package com.sprintsync.ui.views.project_view.member

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.view_models.TeamViewModel
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.member.MemberCard
import com.sprintsync.ui.theme.spacing

@Composable
fun Member(projectId: String?) {
    val teamVM = hiltViewModel<TeamViewModel>()
    val teamState by teamVM.state.collectAsStateWithLifecycle()
    val teamList = teamState.dtoList

    LaunchedEffect(Unit) {
        if (projectId != null) {
            teamVM.getTeamsOfProject(projectId)
        }
    }

    var searchTerm by remember {
        mutableStateOf("")
    }
    Surface {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            SearchBar(placeHolder = "Search a member", onValueChange = { searchTerm = it })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            teamList?.forEach() { team ->
                TeamCard(
                    projectId = projectId,
                    teamName = team.name,
                    memberList = team.members,
                    searchTerm = searchTerm
                )
            }
        }
    }
}

@Composable
fun TeamCard(
    projectId: String?,
    teamName: String,
    memberList: List<MemberDto?>,
    searchTerm: String
) {
    var isOpen by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isOpen = !isOpen }
                .padding(vertical = MaterialTheme.spacing.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sprint $teamName",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        AnimatedVisibility(visible = isOpen) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default
                )
            ) {
                memberList.forEach() { member ->
                    if (member != null && member.name.contains(searchTerm))
                        projectId?.let {
                            MemberCard(
                                projectId = it,
                                member = member,
                            )
                        }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun MemberPreview() {
//	SprintSyncTheme {
//		Member("6524172bb9f63b47c37b739e")
//	}
//}