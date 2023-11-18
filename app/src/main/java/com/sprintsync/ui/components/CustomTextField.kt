package com.sprintsync.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.theme.Purple40

@Composable
fun CommentTextField(
	modifier: Modifier,
	placeholder: String,
	value: String,
	onValueChange: (String) -> Unit
) {
	BasicTextField(
		modifier = modifier,
		value = value,
		onValueChange = onValueChange,
		textStyle = TextStyle(
			color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray
		),
		decorationBox = { innerTextField ->
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.White,
						shape = RoundedCornerShape(8.dp)
					)
					.border(
						1.dp,
						Color(0xFF969EBD),
						shape = RoundedCornerShape(8.dp)
					)
			) {
				if (value.isEmpty()) {
					Text(
						modifier = Modifier.padding(8.dp),
						text = placeholder,
						fontSize = 14.sp,
						color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray
					)
				}
			}
			innerTextField()
		}
	)
}

@Composable
fun ExpandableTextField(
	modifier: Modifier = Modifier,
	surfaceModifier: Modifier = Modifier,
	isPassword: Boolean = false,
	value: String = "",
	onValueChange: ((String) -> Unit)? = null,
	label: String = "Label",
	placeholder: String = "Placeholder",
	leadingIcon: @Composable (() -> Unit)? = null,
	trailingIcon: @Composable (() -> Unit)? = null,
	shape: Shape = OutlinedTextFieldDefaults.shape,
	colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
		focusedBorderColor = Purple40,
		unfocusedBorderColor = Purple40,
		errorLeadingIconColor = MaterialTheme.colorScheme.error,
	),
	isError: Boolean = false,
	errorText: String = "Something is wrong...",
	readOnly: Boolean = false
) {
	var text by remember { mutableStateOf(value) }
	var isVisible by remember { mutableStateOf(!isPassword) }

	Column {
		Surface(modifier = surfaceModifier) {
			OutlinedTextField(
				modifier = modifier,
				value = text,
				onValueChange = {
					text = it
					onValueChange?.invoke(it)
				},
				leadingIcon = leadingIcon,
				trailingIcon = {
					if (isPassword) {
						IconButton(onClick = { isVisible = !isVisible }) {
							Icon(
								painter = painterResource(
									id = if (isVisible) R.drawable.eye_slash else R.drawable.eye
								),
								contentDescription = null,
								tint = Color(0xFF381E72)
							)
						}
					}
					else {
						trailingIcon?.invoke()
					}
				},
				label = { Text(label) },
				placeholder = {
					Text(
						text = placeholder,
						fontSize = 16.sp,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
					)
				},
				visualTransformation = when {
					text.isEmpty() -> PlaceholderTransformation(placeholder)
					!isVisible     -> PasswordVisualTransformation()
					else           -> VisualTransformation.None
				},
				maxLines = 1,
				shape = RoundedCornerShape(16),
				colors = colors,
				isError = isError,
				readOnly = readOnly
			)
		}
		if (isError)
			Text(
				modifier = Modifier,
				text = errorText,
				color = MaterialTheme.colorScheme.error,
				style = MaterialTheme.typography.bodyMedium
			)
	}
}

class PlaceholderTransformation(private val placeholder: String) : VisualTransformation {
	override fun filter(text: AnnotatedString): TransformedText {
		val offsetTranslator = object : OffsetMapping {
			override fun originalToTransformed(offset: Int): Int = 0
			override fun transformedToOriginal(offset: Int): Int = 0
		}

		return TransformedText(AnnotatedString(placeholder), offsetTranslator)
	}
}