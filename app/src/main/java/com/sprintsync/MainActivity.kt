package com.sprintsync

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.ui.components.project_list.CustomFloatingActionButton
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.views.BoardView
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SprintSyncTheme {
					CustomFloatingActionButton()
			}
		}
	}
}

@Preview
@Composable
fun GreetingPreview() {
	BoardView()
}