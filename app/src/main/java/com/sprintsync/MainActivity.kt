package com.sprintsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.view_models.ProjectViewViewModel
import com.sprintsync.ui.views.login.LogInScreen
import com.sprintsync.ui.views.login.SignUpScreen
import com.sprintsync.ui.views.project_view.ProjectList


class MainActivity : ComponentActivity() {
    private val projectViewViewModel: ProjectViewViewModel = ProjectViewViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SprintSyncTheme {
                // A surface container using the 'background' color from the theme
                ProjectList(projectViewViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SprintSyncTheme {
        Greeting("Android")
    }
}