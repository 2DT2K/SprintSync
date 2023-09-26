package com.sprintsync.ui.views


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.`Home-Page-Components`.HomePageIssue
import com.sprintsync.ui.components.`Home-Page-Components`.HomePageViews
import com.sprintsync.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) {innerPadding->
        Column(
            Modifier.padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            HomePageViews()
            HomePageIssue()

        }
    }

}

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}