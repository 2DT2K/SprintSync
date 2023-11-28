package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.sprintsync.ui.theme.Grey80
import com.sprintsync.ui.theme.Purple40

@Composable
fun TaskInputGroup(
    title: String,
    value: String,
    content: @Composable() (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {}
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall
    )
    content?.invoke() ?: TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {}
            ),
        value = value,
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (title == "Point") KeyboardType.Number
            else KeyboardType.Text
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Grey80
        ),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}