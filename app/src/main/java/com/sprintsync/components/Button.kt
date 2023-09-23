package com.sprintsync.components

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomButton(
    type: String = "filled",
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    if (type == "filled") {
        Button(
            onClick = { onClick() },
            modifier = modifier
        ) {
            Text(text)
        }
    } else if (type == "outlined") {
        OutlinedButton(
            onClick = { onClick() },
            modifier = modifier
        ) {
            Text(text)
        }
    }
}

@Composable
fun test() {
    CustomButton(text = "name") {

    }
}