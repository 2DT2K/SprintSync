package com.sprintsync

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sprintsync.auth.UserData

@Composable
fun Profile(
	data: UserData?,
	signOut: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		AsyncImage(
			modifier = Modifier
				.size(128.dp)
				.clip(CircleShape),
			model = data?.profilePictureUrl?.toString(),
			contentDescription = null,
			contentScale = ContentScale.Crop
		)

		Spacer(modifier = Modifier.height(16.dp))

		Text(
			text = "Welcome back,",
			fontSize = 32.sp
		)
		Text(
			text = data?.name ?: "Anonymous",
			fontSize = 32.sp
		)

		Spacer(modifier = Modifier.height(128.dp))

		Button(onClick = signOut) {
			Text(
				text = "Sign out",
				fontSize = 32.sp
			)
		}
	}
}