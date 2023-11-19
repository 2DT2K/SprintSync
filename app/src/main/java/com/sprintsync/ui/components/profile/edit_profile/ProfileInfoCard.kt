package com.sprintsync.ui.components.profile.edit_profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.sprintsync.ui.theme.Purple40

@Composable
fun ProfileInfoCard(
	title: String,
	value: String,
	content: @Composable() (() -> Unit)? = null,
	onValueChange: (String) -> Unit = {}
) {
	Text(
		text = title,
		fontWeight = FontWeight.Bold
	)
	content?.invoke() ?: OutlinedTextField(
		modifier = Modifier.fillMaxWidth(),
		value = value,
		onValueChange = { onValueChange(it) },
		shape = RoundedCornerShape(16),
		colors = OutlinedTextFieldDefaults.colors(
			focusedBorderColor = Purple40,
			unfocusedBorderColor = Purple40
		),
		visualTransformation = if (title == "Password")
			PasswordVisualTransformation()
		else VisualTransformation.None
	)
}