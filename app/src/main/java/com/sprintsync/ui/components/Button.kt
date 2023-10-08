package com.sprintsync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.theme.Purple40

@Composable
fun CustomButton(
    type: String = "filled",
    modifier: Modifier = Modifier,
    surfaceModifier: Modifier = Modifier,
    text: String,
    icon: @Composable() (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Surface(modifier = surfaceModifier) {
        if (type == "filled") {
            Button(
                onClick = { onClick() },
                modifier = modifier,
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                if (icon != null) {
                    icon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = text)
            }
        } else if (type == "outlined") {
            OutlinedButton(
                onClick = { onClick() },
                modifier = modifier,
                shape = RoundedCornerShape(16),
            ) {
                if (icon != null) {
                    icon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = text, color = Purple40)
            }
        }
    }
}

@Composable
fun test() {
    CustomButton(text = "name") {

    }
}