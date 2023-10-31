package com.sprintsync.ui.components

import androidx.compose.foundation.layout.RowScope
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
	modifier: Modifier = Modifier,
	surfaceModifier: Modifier = Modifier,
	text: String = "",
	isFilled: Boolean = true,
	onClick: () -> Unit = {},
	colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Purple40),
	icon: @Composable (() -> Unit)? = null,
	content: @Composable (RowScope.() -> Unit)? = null,
) {
	Surface(modifier = surfaceModifier) {
		if (isFilled)
			Button(
				modifier = modifier,
				onClick = onClick,
				shape = RoundedCornerShape(16),
				colors = colors
			) {
				icon?.let {
					it()
					Spacer(modifier = Modifier.width(8.dp))
				}
				Text(text = text)
				content?.let { it() }
			}
		else
			OutlinedButton(
				modifier = modifier,
				onClick = onClick,
				shape = RoundedCornerShape(16),
			) {
				icon?.let {
					it()
					Spacer(modifier = Modifier.width(8.dp))
				}
				Text(text = text, color = Purple40)
				content?.let { it() }
			}
	}
}
