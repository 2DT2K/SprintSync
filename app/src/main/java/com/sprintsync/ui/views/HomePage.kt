package com.sprintsync.ui.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.HomeTopAppBar
import com.sprintsync.ui.components.homepage.HomePageIssue
import com.sprintsync.ui.components.homepage.HomePageViews
import com.sprintsync.ui.view_models.MemberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val memberViewModel: MemberViewModel = viewModel()
    val members = memberViewModel.posts.collectAsState()
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { memberViewModel.getMembers() }) {
            Text(text = "Get members")
        }
        members.value.map {
            Text(text = it.name)
        }
        HomePageViews()
        HomePageIssue()
    }
}

@Preview
@Composable
fun HomePagePreview() {
	HomePage()
}