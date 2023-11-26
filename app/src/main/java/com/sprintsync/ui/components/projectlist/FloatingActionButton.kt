package com.sprintsync.ui.components.projectlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.ExpandableTextField
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Grey80
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFloatingActionButton(
	showBottomSheet: Boolean,
	changeSheetState: (Boolean) -> Unit
) {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	val scope = rememberCoroutineScope()

	if (showBottomSheet) {
		ModalBottomSheet(
			modifier = Modifier.fillMaxHeight(),
			onDismissRequest = {
				changeSheetState(false)
			},
			sheetState = sheetState,
			windowInsets = WindowInsets(0),
			dragHandle = {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 24.dp),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.Center
				) {
					Box(
						modifier = Modifier
							.padding(0.dp)
							.width(32.dp)
							.height(4.dp)
							.background(
								color = Color(0xFF79747E),
								shape = RoundedCornerShape(size = 100.dp)
							)
					)
				}
			}
		) {
			Text(
				text = "Project name",
				modifier = Modifier.padding(start = 24.dp),
				fontWeight = FontWeight(600)
			)
			ExpandableTextField(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 24.dp, end = 24.dp),
				value = "",
				label = "",
				placeholder = "Please enter project name",
				onValueChange = {

				},
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                )
			)

			Spacer(modifier = Modifier.height(16.dp))

			Text(
				text = "Add a description",
				modifier = Modifier.padding(start = 24.dp),
				fontWeight = FontWeight(600)
			)

			ExpandableTextField(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 24.dp, end = 24.dp),
				value = "",
				label = "",
				placeholder = "Please enter description",
				onValueChange = {

				},
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                )
			)

			Spacer(modifier = Modifier.height(24.dp))

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(end = 8.dp)
			) {
				IconButton(
					modifier = Modifier
						.align(Alignment.CenterEnd)
						.background(
							color = Grey80,
							shape = RoundedCornerShape(100)
						),
					onClick = { /*TODO*/ }) {
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(start = 4.dp)
							.size(48.dp),
						contentAlignment = Alignment.Center
					) {
						Icon(
							modifier = Modifier
								.padding(0.dp)
								.size(32.dp),
							painter = painterResource(id = R.drawable.send),
							contentDescription = "create project",
							tint = Grey40
						)
					}
				}
			}


			Spacer(
				Modifier
					.background(Color.Transparent)
					.fillMaxWidth()
					.windowInsetsPadding(
						WindowInsets.systemBars.only(WindowInsetsSides.Bottom)
					)
			)
		}
	}
}

@Preview
@Composable
fun PreviewFloatingActionButton() {
	var isOpen by remember { mutableStateOf(true) }
	SprintSyncTheme {
		Button(onClick = { isOpen = true }) {
			Text(text = "click")
		}
		CustomFloatingActionButton(isOpen) { state -> isOpen = state }
	}
}