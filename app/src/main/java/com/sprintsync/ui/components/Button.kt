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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.theme.Purple40

@Composable
fun CustomButton(
	modifier: Modifier = Modifier,
	surfaceModifier: Modifier = Modifier,
	onClick: () -> Unit = {},
	isFilled: Boolean = true,
	text: String = "",
	fontSize: TextUnit = TextUnit.Unspecified,
	shape: Shape = RoundedCornerShape(16),
	colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Purple40),
	icon: @Composable (() -> Unit)? = null,
	content: @Composable (RowScope.() -> Unit)? = null,
) {
	Surface(modifier = surfaceModifier) {
		if (isFilled)
			Button(
				modifier = modifier,
				onClick = onClick,
				shape = shape,
				colors = colors
			) {
				icon?.let {
					it()
					Spacer(modifier = Modifier.width(8.dp))
				}
				Text(
					text = text,
					fontSize = fontSize
				)
				content?.invoke(this)
			}
		else
			OutlinedButton(
				modifier = modifier,
				onClick = onClick,
				shape = shape,
			) {
				icon?.let {
					it()
					Spacer(modifier = Modifier.width(8.dp))
				}
				Text(
					text = text,
					color = Purple40,
					fontSize = fontSize
				)
				content?.invoke(this)
			}
	}
}