package com.sprintsync.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(
	modifier: Modifier = Modifier,
	isSheetShown: Boolean,
	changeVisibility: (Boolean) -> Unit,
	sheetContent: @Composable() (ColumnScope.() -> Unit),
) {
	val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
	val scope = rememberCoroutineScope()

	if (isSheetShown) {
		ModalBottomSheet(
			modifier = modifier,
			onDismissRequest = {
				changeVisibility(false)
			},
			sheetState = sheetState,
			windowInsets = WindowInsets(0),
		) {

			sheetContent()

			Spacer(
				Modifier
					.windowInsetsBottomHeight(WindowInsets(bottom = 64.dp))
			)
		}
	}
}