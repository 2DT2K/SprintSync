package com.sprintsync.ui.components.project_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFloatingActionButton() {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }

	Scaffold(
		floatingActionButton = {
			ExtendedFloatingActionButton(
				text = { Text("Show bottom sheet") },
				icon = { Icon(Icons.Filled.Add, contentDescription = "") },
				onClick = {
					showBottomSheet = true
				}
			)
		}
	) { contentPadding ->
		// Screen content

		if (showBottomSheet) {

            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
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
                // Sheet content
                Text(
                    text = "Project name",
                    modifier = Modifier.padding(start = 24.dp),
                    fontWeight = FontWeight(600)
                )
				ExpandableTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    value = "",
                    label = "",
                    placeholder = "Add a description",
                    onValueChange = {

					},
					colors = OutlinedTextFieldDefaults.colors(
						focusedBorderColor = Color.Transparent,
						unfocusedBorderColor = Color.Transparent,
					)
				)

				Box(modifier = Modifier.fillMaxWidth()) {
					IconButton(
						modifier = Modifier.align(Alignment.CenterEnd),
						onClick = { /*TODO*/ }) {
						Icon(
							painter = painterResource(id = R.drawable.send),
							contentDescription = "create project"
						)
					}
				}

				Spacer(
					Modifier
						.background(Color.Transparent)
						.fillMaxWidth()
						.windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
				)
			}
		}
	}
}

@Preview
@Composable
fun PreviewFloatingActionButton() {
	SprintSyncTheme {
		CustomFloatingActionButton()
	}
}