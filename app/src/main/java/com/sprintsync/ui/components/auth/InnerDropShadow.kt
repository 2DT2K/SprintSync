package com.sprintsync.ui.components.auth

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.innerShadow(
	color: Color = Color.Black,
	blur: Dp = 0.dp,
	cornersRadius: Dp = 0.dp,
	spread: Dp = 0.dp,
	offsetX: Dp = 0.dp,
	offsetY: Dp = 0.dp
) = drawWithContent {

	drawContent()

	val rect = Rect(Offset.Zero, size)
	val paint = Paint()

	drawIntoCanvas {

		paint.color = color
		paint.isAntiAlias = true
		it.saveLayer(rect, paint)
		it.drawRoundRect(
			left = rect.left,
			top = rect.top,
			right = rect.right,
			bottom = rect.bottom,
			cornersRadius.toPx(),
			cornersRadius.toPx(),
			paint
		)
		val frameworkPaint = paint.asFrameworkPaint()
		frameworkPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
		if (blur.toPx() > 0)
			frameworkPaint.maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)

		val top = if (offsetY > 0.dp) rect.top + offsetY.toPx() else rect.top
		val bottom = if (offsetY < 0.dp) rect.bottom + offsetY.toPx() else rect.bottom
		val left = if (offsetX > 0.dp) rect.left + offsetX.toPx() else rect.left
		val right = if (offsetX < 0.dp) rect.right + offsetX.toPx() else rect.right

		paint.color = Color.Black

		it.drawRoundRect(
			top = top + spread.toPx() / 2,
			bottom = bottom,
			left = left + spread.toPx() / 2,
			right = right,
			radiusX = cornersRadius.toPx(),
			radiusY = cornersRadius.toPx(),
			paint = paint
		)

		frameworkPaint.xfermode = null
		frameworkPaint.maskFilter = null
	}
}