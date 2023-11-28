package com.sprintsync.ui.components.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.ui.components.CustomIconButton
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.spacing

@Composable
fun HomePageViews(navController: NavController? = null) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.smallMedium,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Views",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(8.dp)
                        )
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.three_dot),
                                contentDescription = ""
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
                    ) {
                        CustomIconButton(
                            iconID = R.drawable.folder_fill,
                            content = "Project",
                            backgroundColor = 0xFFFF71D1,
                            onClick = {
                                navController?.navigate(Screens.ProjectRoute.route)
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        CustomIconButton(
                            iconID = R.drawable.task,
                            content = "Task",
                            backgroundColor = 0xFFD47AFE,
                            onClick = {
                                navController?.navigate(Screens.Tasks.route)
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
                    ) {
                        CustomIconButton(
                            iconID = R.drawable.timelapse,
                            content = "Timeline",
                            backgroundColor = 0xFFFF829E,
                            onClick = {
                                navController?.navigate(Screens.Timeline.route)
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        CustomIconButton(
                            iconID = R.drawable.calendar,
                            content = "Calendar",
                            backgroundColor = 0xFFFFA874,
                            onClick = {
                                navController?.navigate(Screens.Calendar.route)
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuViewsPreview() {
	HomePageViews()
}