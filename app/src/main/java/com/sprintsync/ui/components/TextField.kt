package com.sprintsync.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    type: String? = "normal",
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "Email",
    placeholder: String = "Please Enter Your Email",
    isVisible: Boolean? = true,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
): Unit {
    var text by remember { mutableStateOf(value) }
    var passwordVisible by remember { mutableStateOf(isVisible) }
    if (type == "hidden") passwordVisible = false;

    Surface {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            onValueChange = { text = it },
            leadingIcon = { leadingIcon },
            trailingIcon = {
//            val image = null
//
//            // Please provide localized description for accessibility services
//            val description = if (passwordVisible == true) "Hide password" else "Show password"
//
//            IconButton(onClick = { passwordVisible = !passwordVisible!! }) {
//                Icon(imageVector = image, description)
//            }
            },
            label = { Text(label) },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            visualTransformation = if (text.isEmpty())
                PlaceholderTransformation(placeholder) else if (passwordVisible != null &&
                passwordVisible == false
            ) PasswordVisualTransformation() else VisualTransformation.None,
            maxLines = 1,
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

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    SprintSyncTheme {

    }
}
