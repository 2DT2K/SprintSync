package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R

@Composable
fun ChartTitle(
	sprintName: String,
	onSprintNameChange: (String) -> Unit
) {
	var expanded by remember {
		mutableStateOf(false)
	}
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.fillMaxWidth()
	) {
		Text(
			text = "Title",
			style = TextStyle(
				fontSize = 18.sp,
				fontWeight = FontWeight(600),
				color = Color(0xFF243465),
				letterSpacing = 0.28.sp,
			)
		)
		Box {
			OutlinedButton(
				onClick = {
					expanded = !expanded
				},
				contentPadding = PaddingValues(8.dp, 4.dp, 8.dp, 4.dp),
				modifier = Modifier
					.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
			) {
				Text(
					text = sprintName,
					style = TextStyle(
						fontSize = 12.sp,
						fontWeight = FontWeight(600),
						color = Color(0xFF848A9C),
						letterSpacing = 0.18.sp,
					)
				)
				Icon(
					painter = painterResource(id = R.drawable.mini_down_arrow),
					contentDescription = "image description",
					modifier = Modifier
						.width(16.dp)
						.height(16.dp)
				)
			}
			DropdownMenu(
				expanded = expanded,
				onDismissRequest = { expanded = false },
				modifier = Modifier.clickable { expanded = !expanded }
			) {
				listOf("Sprint 1", "Sprint 2", "Sprint 3").forEach { sprint ->
					TextButton(
						onClick = {
							onSprintNameChange(sprint)
							expanded = false
						}
					) {
						Text(
							text = sprint,
							style = TextStyle(
								fontSize = 12.sp,
								fontWeight = FontWeight(600),
								color = Color(0xFF848A9C),
								letterSpacing = 0.18.sp
							)
						)
					}
				}
			}
		}
	}
}