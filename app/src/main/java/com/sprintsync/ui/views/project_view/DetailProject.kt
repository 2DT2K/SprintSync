package com.sprintsync.ui.views.project_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.theme.SprintSyncTheme

data class GridItem(val id: Int, val text: String)

@Composable
fun DetailProject() {
	var text by remember {
		mutableStateOf("")
	}
	val gridItems = listOf(
		GridItem(R.drawable.dashboard, "Board"),
		GridItem(R.drawable.backlog, "BackLog"),
		GridItem(R.drawable.timelapse, "Timeline"),
		GridItem(R.drawable.tasks, "Tasks"),
		GridItem(R.drawable.files, "Files"),
		GridItem(R.drawable.people, "People"),
		GridItem(R.drawable.report, "Report"),
	)

	Column(
		modifier = Modifier
			.padding(start = 24.dp, end = 24.dp, top = 24.dp)
			.fillMaxSize(),
		verticalArrangement = Arrangement.spacedBy(32.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(24.dp)
		) {
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
			Text(
				text = "SprintSync",
				style = TextStyle(
					fontSize = 32.sp,
					fontWeight = FontWeight(500),
					color = Color(0xFF1D192B),
				)
			)
		}
		TextField(
			value = text,
			onValueChange = { text = it },
			modifier = Modifier.fillMaxWidth(),
			placeholder = { Text(text = "Search in this project") },
			leadingIcon = {
				Icon(painterResource(id = R.drawable.search), contentDescription = "search bar")
			},
			colors = TextFieldDefaults.colors(
				focusedContainerColor = Color(0xFFD6D2D8),
				unfocusedContainerColor = Color(0xFFD6D2D8),
				disabledTextColor = Color.Transparent,
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent,
				disabledIndicatorColor = Color.Transparent
			),
			shape = RoundedCornerShape(size = 24.dp)
		)
		LazyVerticalGrid(
			columns = GridCells.Fixed(3),
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp)
		) {
			items(gridItems) {
				CustomButton(
					surfaceModifier = Modifier.height(80.dp),
					colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8E1EC)),
					onClick = {}
				) {
					Column(
						modifier = Modifier.fillMaxSize(),
						verticalArrangement = Arrangement.Center
					) {
						Icon(
							painter = painterResource(id = it.id),
							contentDescription = it.text,
							tint = Color.Black
						)
						Text(text = it.text, color = Color.Black)
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DetailProjectPreview() {
	SprintSyncTheme {
		DetailProject()
	}
}