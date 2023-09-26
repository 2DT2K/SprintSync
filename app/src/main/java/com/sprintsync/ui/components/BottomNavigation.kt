package com.sprintsync.ui.components


import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.R


@Composable
fun BottomNavigation() {
    var selectedItem: Number by remember {
        mutableStateOf(0)
    }
    val items = listOf<String>("Home", "Project", "Calendar", "Profile")
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    when (item) {
                        "Home" -> Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = null
                        )

                        "Project" -> Image(
                            painter = painterResource(id = R.drawable.folder_share),
                            contentDescription = null
                        )

                        "Calendar" -> Image(
                            painter = painterResource(id = R.drawable.calendar_month),
                            contentDescription = null
                        )

                        "Profile" -> Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(item) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavigation()
}