package com.sprintsync

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.sprintsync.auth.SignInState

@Composable
fun SignInScreen(
	state: SignInState,
	onSignInClick: () -> Unit
) {
	val context = LocalContext.current

	LaunchedEffect(state.errorMessage) {
		state.errorMessage?.let {
			Toast
				.makeText(context, it, Toast.LENGTH_LONG)
				.show()
		}
	}

	Box(
		modifier = Modifier
			.fillMaxSize()
			.scale(2f),
		contentAlignment = Alignment.Center
	) {
		IconButton(onClick = onSignInClick) {
			Icon(
				painter = painterResource(id = R.drawable.google),
				contentDescription = null,
				tint = Color.Unspecified
			)
		}
	}
}