package com.sprintsync

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignIn(
	signInWithPassword: (String, String) -> Unit,
	signInWithGoogle: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 32.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		var email by remember { mutableStateOf("") }
		var password by remember { mutableStateOf("") }

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
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
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
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
		)

		Spacer(modifier = Modifier.height(8.dp))

		Row(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			horizontalArrangement = Arrangement.End
		) {
			ClickableText(
				text = AnnotatedString("Forgot Password?"),
				style = TextStyle(color = Color.Red),
				onClick = { /*TODO*/ }
			)
		}

		Spacer(modifier = Modifier.height(48.dp))

		Button(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			shape = RoundedCornerShape(8.dp),
			onClick = { signInWithPassword(email, password) }
		) {
			Text(text = "SIGN IN")
		}

		Spacer(modifier = Modifier.height(16.dp))

		Row(
			modifier = Modifier.width(TextFieldDefaults.MinWidth),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(text = "Don't have an account?")

			Spacer(modifier = Modifier.width(8.dp))

			ClickableText(
				text = AnnotatedString("Sign Up"),
				style = TextStyle(color = Color.Red),
				onClick = { /*TODO*/ }
			)
		}

		Spacer(modifier = Modifier.height(64.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Divider(
				modifier = Modifier.width(128.dp),
				thickness = 1.dp
			)

			Text(
				modifier = Modifier.padding(8.dp),
				text = "OR",
				fontSize = 16.sp,
				color = Color.Gray
			)

			Divider(
				modifier = Modifier.width(128.dp),
				thickness = 1.dp
			)
		}

		OutlinedButton(onClick = signInWithGoogle) {
			Icon(
				modifier = Modifier.size(ButtonDefaults.IconSize),
				painter = painterResource(id = R.drawable.google),
				contentDescription = null,
				tint = Color.Unspecified
			)

			Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

			Text(text = "Continue with Google")
		}
	}
}