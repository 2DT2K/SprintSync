package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sprintsync.R
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    type: String? = "normal",
    modifier: Modifier = Modifier,
    surfaceModifier: Modifier = Modifier,
    value: String = "",
    label: String = "Email",
    placeholder: String = "Please Enter Your Email",
    isVisible: Boolean? = true,
    isError: Boolean = false,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Purple40,
        unfocusedBorderColor = Purple40
    ),
    onValueChange: ((String) -> Unit)? = null,
    errorText: String = "please redo"
) {
    var text by remember { mutableStateOf(value) }
    var passwordVisible by remember { mutableStateOf(isVisible) }
    if (type == "hidden") passwordVisible = false;

    Column {
        Surface(modifier = surfaceModifier) {
            OutlinedTextField(
                modifier = modifier,
                value = text,
                onValueChange = {
                    text = it
                    if (onValueChange != null) {
                        onValueChange(it)
                    }
                },
                leadingIcon = leadingIcon,
                trailingIcon =
                if (trailingIcon != null) {
                    {
                        IconButton(onClick = { passwordVisible = !passwordVisible!! }) {
                            if (passwordVisible == true) trailingIcon()
                            else Icon(
                                painter = painterResource(id = R.drawable.invisibility),
                                contentDescription = null,
                                tint = Color(0xFF381E72)
                            )
                        }
                    }
                } else {
                    null
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
                visualTransformation = if (text.isEmpty())
                    PlaceholderTransformation(placeholder) else if (passwordVisible != null &&
                    passwordVisible == false
                ) PasswordVisualTransformation() else VisualTransformation.None,
                maxLines = 1,
                shape = RoundedCornerShape(16),
                colors = colors,
                isError = isError
            )
        }
        if (isError) Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
        )
    }
}

class PlaceholderTransformation(private val placeholder: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return placeHolderFilter(text, placeholder)
    }
}

fun placeHolderFilter(text: AnnotatedString, placeholder: String): TransformedText {

    var out = placeholder

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return 0
        }

        override fun transformedToOriginal(offset: Int): Int {
            return 0
        }
    }

    return TransformedText(AnnotatedString(placeholder), numberOffsetTranslator)
}
