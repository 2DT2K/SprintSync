package com.sprintsync.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
	AnimatedVisibility(
		visibleState = MutableTransitionState(
			initialState = false
		).apply { targetState = true },
		modifier = Modifier.fillMaxSize(),
		enter = slideInHorizontally(
			initialOffsetX = { -it }
		),
	) {
		content()
	}
}