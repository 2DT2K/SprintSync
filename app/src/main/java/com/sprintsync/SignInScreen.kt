package com.sprintsync

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun SignInScreen(signIn: () -> Unit) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.scale(1.5f),
		contentAlignment = Alignment.Center
	) {
		IconButton(onClick = signIn) {
			Icon(
				painter = painterResource(id = R.drawable.google),
				contentDescription = null,
				tint = Color.Unspecified
			)
		}
	}
}