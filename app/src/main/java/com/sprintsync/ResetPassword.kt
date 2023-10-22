package com.sprintsync

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun ResetPassword(
	resetPassword: (String) -> Unit,
	back: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		var email by remember { mutableStateOf("") }

		OutlinedTextField(
			value = email,
			onValueChange = { email = it },
			label = { Text(text = "Email") },
			leadingIcon = {
				Icon(
					imageVector = Icons.Rounded.Email,
					contentDescription = null
				)
			},
			shape = RoundedCornerShape(8.dp),
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
			isError = email.isEmpty()
		)

		Spacer(modifier = Modifier.height(48.dp))

		Button(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			shape = RoundedCornerShape(8.dp),
			onClick = {
				resetPassword(email)
				back()
			},
			enabled = email.isNotEmpty()
		) {
			Text(text = "RESET PASSWORD")
		}

		Spacer(modifier = Modifier.height(8.dp))

		FilledTonalButton(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			shape = RoundedCornerShape(8.dp),
			onClick = back,
		) {
			Text(text = "BACK")
		}
	}
}