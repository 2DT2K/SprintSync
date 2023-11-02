package com.sprintsync.ui.components.project_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.theme.Yellow80
import com.sprintsync.ui.view_models.ProjectViewViewModel

@Composable
fun ProjectCard(
	modifier: Modifier = Modifier,
	index: Int = -1,
	project: ProjectViewViewModel.ProjectList,
	onChange: ((Int) -> Unit)? = null
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.height(40.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Row(
			modifier = Modifier,
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(24.dp)
		)
		{
			Box(
				modifier = Modifier
					.size(40.dp)
					.border(
						width = 2.dp,
						color = Color(0xFF444444),
						shape = RoundedCornerShape(size = 7.dp)
					), contentAlignment = Alignment.Center
			)
			{
				Image(
					modifier = Modifier
						.size(24.dp)
						.clip(CircleShape),
					painter = painterResource(id = R.drawable.email),
					contentDescription = "project avatar",
					contentScale = ContentScale.Crop,
				)
			}

			Column(modifier = Modifier) {
				Text(
					text = project.projectName,
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight(400),
						color = Color(0xFF000000),
					),
					textAlign = TextAlign.Center
				)
				Text(
					text = project.projectKey,
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight.Bold,
						color = Color(0xFF000000),
					), textAlign = TextAlign.Center
				)
			}
		}

		IconButton(onClick = {
			if (onChange != null) {
				onChange(index)
			}
		}) {
			if (project.isStarred) Icon(
				painter = painterResource(id = R.drawable.selected_star),
				contentDescription = "starred",
				tint = Yellow80
			)
			else Icon(
				painter = painterResource(id = R.drawable.unselected_star),
				contentDescription = "unstarred"
			)
		}
	}
}