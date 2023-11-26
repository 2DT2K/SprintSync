import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.theme.SprintSyncTheme

@Composable
fun Indicator(
	size: Dp = 32.dp, // indicator size
	sweepAngle: Float = 45f, // angle (length) of indicator arc
	color: Color = colorScheme.primary, // color of indicator arc line
	strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth // width of circle and arc lines
) {
	// Animation
	val transition = rememberInfiniteTransition(label = "RoundProgressIndicatorArcAngle")
	val currentArcStartAngle by transition.animateValue(
		0,
		360,
		Int.VectorConverter,
		infiniteRepeatable(
			animation = tween(
				durationMillis = 1100,
				easing = LinearEasing
			)
		), label = "RoundProgressIndicatorArcAngle"
	)

    // Draw
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    }

	Canvas(
		modifier = Modifier
			.progressSemantics() // (optional) for Accessibility services
			.size(size)
			.padding(strokeWidth / 2)
	) {
		// Draw background circle
		drawCircle(Color(0xFFD9D9D9), style = stroke)

		// Draw arc
		drawArc(
			color,
			startAngle = currentArcStartAngle.toFloat() - 90,
			sweepAngle = sweepAngle,
			useCenter = false,
			style = stroke
		)
	}
}

@Preview(showBackground = true)
@Composable
fun IndicatorPreview() {
	SprintSyncTheme {
		Indicator()
	}
}
