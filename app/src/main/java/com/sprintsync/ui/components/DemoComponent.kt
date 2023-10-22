package com.sprintsync.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sprintsync.ui.view_models.DemoViewModel
import kotlin.random.Random

@Composable
fun DemoComponent(demoViewModel: DemoViewModel = viewModel()) {
	val name = demoViewModel.name.collectAsState()

	fun randomizeName() {
		val randomName = Random
			.nextLong()
			.toString()
		demoViewModel.onChangeName(randomName)
	}

	Row(modifier = Modifier.fillMaxWidth()) {
		Text(text = name.value)
		Button(onClick = { randomizeName() }) {
			Text(text = "Randomize Name")
		}
	}
}