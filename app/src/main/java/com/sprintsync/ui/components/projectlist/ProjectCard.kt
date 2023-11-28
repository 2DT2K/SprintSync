package com.sprintsync.ui.components.projectlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.data.view_models.ProjectViewViewModel
import com.sprintsync.ui.theme.Yellow80
import com.sprintsync.ui.theme.spacing

@Composable
fun ProjectCard(
    modifier: Modifier = Modifier,
    index: Int = -1,
    project: ProjectDto,
    onClick: () -> Unit,
    onChange: ((Int) -> Unit)? = null,
) {
    Surface(onClick = onClick, modifier = modifier.fillMaxSize()) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            )
            {
                ProjectAvatar()
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically)
                ) {
                    Text(
                        text = project.code,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = project.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            IconButton(onClick = {
                if (onChange != null) {
                    onChange(index)
                }
            }) {
                //TODO: add star later
                if (true) Icon(
                    painter = painterResource(id = R.drawable.selected_star),
                    contentDescription = "starred",
                    tint = Yellow80
                )
                else Icon(
                    painter = painterResource(id = R.drawable.unselected_star),
                    contentDescription = "unstarred",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}