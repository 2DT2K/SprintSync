package com.sprintsync.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Text(
	text: String,
	modifier: Modifier = Modifier,
	color: Color = Color.Black,
	fontSize: TextUnit = 16.sp,
	fontStyle: FontStyle? = null,
	fontWeight: FontWeight? = FontWeight(400),
	fontFamily: FontFamily? = null,
	letterSpacing: TextUnit = 0.5.sp,
	textDecoration: TextDecoration? = null,
	textAlign: TextAlign? = null,
	lineHeight: TextUnit = 16.sp,
	overflow: TextOverflow = TextOverflow.Clip,
	softWrap: Boolean = true,
	maxLines: Int = Int.MAX_VALUE,
	minLines: Int = 1,
	onTextLayout: (TextLayoutResult) -> Unit = {},
	style: TextStyle = LocalTextStyle.current
) {
	Text(
		text = text,
		modifier = modifier,
		color = color,
		fontSize = fontSize,
		fontStyle = fontStyle,
		fontWeight = fontWeight,
		fontFamily = fontFamily,
		letterSpacing = letterSpacing,
		textDecoration = textDecoration,
		textAlign = textAlign,
		lineHeight = lineHeight,
		overflow = overflow,
		softWrap = softWrap,
		maxLines = maxLines,
		minLines = minLines,
		onTextLayout = onTextLayout,
		style = style
	)
}