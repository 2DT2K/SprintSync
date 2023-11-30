package com.sprintsync.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(

)

private val LightColorScheme = lightColorScheme(
	primary = Purple50,
	onPrimary = Color.White,
	primaryContainer = Purple60,
	onPrimaryContainer = TaskType,
	inversePrimary = Grey20,
	secondary = Blue90,
	onSecondary = Purple50,
	secondaryContainer = Grey100,
	onSecondaryContainer = Grey10,
	tertiary = Orange90,
	onTertiary = Orange20,
	background = PurpleBlue100,
	onBackground = Purple20,
	surface = Color.White,
	onSurface = Purple20,
	onSurfaceVariant = Grey40,
	error = Red20,
	onError = Color.White,
	errorContainer = Red60,
	onErrorContainer = Red10,
	outline = PurpleGIDO,
	outlineVariant = Grey70,
)

@Composable
fun SprintSyncTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	// Dynamic color is available on Android 12+
	dynamicColor: Boolean = true,
	content: @Composable () -> Unit
) {
//	val colorScheme = when {
//		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//			val context = LocalContext.current
//			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//		}
//
//		darkTheme                                                      -> DarkColorScheme
//		else                                                           -> LightColorScheme
//	}
	val colorScheme = LightColorScheme
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			val window = (view.context as Activity).window
			window.statusBarColor = colorScheme.primary.toArgb()
			WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
		}
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography,
		content = content
	)
}