package com.sprintsync.ui.components

import Indicator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog(alertText: String) {
	AlertDialog(
		onDismissRequest = { },
		properties = DialogProperties(
			usePlatformDefaultWidth = false // disable the default size so that we can customize it
		)
	) {
		Column(
			modifier = Modifier
				.padding(start = 32.dp, end = 32.dp) // margin
				.background(color = Color.White, shape = RoundedCornerShape(16.dp))
				.padding(top = 32.dp, bottom = 32.dp), // inner padding
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Indicator(
				size = 32.dp,
				sweepAngle = 45f,
				color = MaterialTheme.colorScheme.primary,
				strokeWidth = 4.dp
			)

			Spacer(modifier = Modifier.height(32.dp))

			Text(
				text = alertText,
				style = MaterialTheme.typography.bodyMedium,
			)
		}
	}
}

@Preview
@Composable
fun LoadingDialogPreview() {
	SprintSyncTheme {
		LoadingDialog("Please wait...")
	}
}