package com.sprintsync.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.theme.SprintSyncTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    type: String? = "normal",
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "Email",
    placeholder: String = "Please Enter Your Email",
    isVisible: Boolean? = true
) {
    var text by remember { mutableStateOf(value) }
    var passwordVisible by remember { mutableStateOf(isVisible) }
    if (type == "hidden") passwordVisible = false;

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        leadingIcon = {},
        label = { Text(label) },
        placeholder = { Text(text = placeholder) },
        visualTransformation = if (text.isEmpty())
            PlaceholderTransformation(placeholder) else if (passwordVisible != null && passwordVisible == false) PasswordVisualTransformation() else VisualTransformation.None,
    )


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
        CustomTextField(
            value = "",
        )
    }
}
