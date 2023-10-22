package com.sprintsync

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SignUp(
	signUpWithPassword: (String, String) -> Unit,
	signIn: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		var email by remember { mutableStateOf("") }
		var password by remember { mutableStateOf("") }
		var confirmPassword by remember { mutableStateOf("") }

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

		OutlinedTextField(
			value = password,
			onValueChange = { password = it },
			label = { Text(text = "Password") },
			visualTransformation = PasswordVisualTransformation(),
			leadingIcon = {
				Icon(
					imageVector = Icons.Rounded.Lock,
					contentDescription = null
				)
			},
			shape = RoundedCornerShape(8.dp),
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
			isError = password.isEmpty()
		)

		OutlinedTextField(
			value = confirmPassword,
			onValueChange = { confirmPassword = it },
			label = { Text(text = "Confirm Password") },
			visualTransformation = PasswordVisualTransformation(),
			leadingIcon = {
				Icon(
					imageVector = Icons.Rounded.Lock,
					contentDescription = null
				)
			},
			shape = RoundedCornerShape(8.dp),
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
			isError = password != confirmPassword
		)

		Spacer(modifier = Modifier.height(48.dp))

		Button(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			shape = RoundedCornerShape(8.dp),
			onClick = { signUpWithPassword(email, password) },
			enabled = email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword
		) {
			Text(text = "SIGN UP")
		}

		Row(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(text = "Already have an account?")

			Spacer(modifier = Modifier.width(8.dp))

			ClickableText(
				text = AnnotatedString("Sign In"),
				style = TextStyle(color = Color.Red),
				onClick = { signIn() }
			)
		}
	}
}