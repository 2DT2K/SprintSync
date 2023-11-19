package com.sprintsync.ui.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.homepage.HomePageIssue
import com.sprintsync.ui.components.homepage.HomePageViews

@Composable
fun HomePage() {
	Column(
		Modifier
			.padding(16.dp)
			.verticalScroll(rememberScrollState()),
		verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		HomePageViews()
		HomePageIssue()
	}
}

@Preview
@Composable
fun HomePagePreview() {
	HomePage()
}