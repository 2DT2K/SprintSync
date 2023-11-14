package com.sprintsync.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sprintsync.R

// Set of Material typography styles to start with
val Typography = Typography(
	bodyLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	),
)

val provider = GoogleFont.Provider(
	providerAuthority = "com.google.android.gms.fonts",
	providerPackage = "com.google.android.gms",
	certificates = R.array.com_google_android_gms_fonts_certs
)
val poppins = GoogleFont("Poppins")
val openSans = GoogleFont("Open Sans")

val fontPoppins = FontFamily(
	Font(googleFont = poppins, fontProvider = provider)
)
val fontOpenSans = FontFamily(
	Font(googleFont = openSans, fontProvider = provider)
)

@Preview
@Composable
fun TestText() {
	Column {
		Text(
			text = "lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
			style = TextStyle(
				fontFamily = fontPoppins,
				fontWeight = FontWeight.Normal,
				fontSize = 16.sp,
				lineHeight = 24.sp,
				letterSpacing = 0.5.sp,
				textAlign = TextAlign.Center
			)
		)
		Text(
			text = "lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
			style = TextStyle(
				fontFamily = fontOpenSans,
				fontWeight = FontWeight.Normal,
				fontSize = 16.sp,
				lineHeight = 24.sp,
				letterSpacing = 0.5.sp,
				textAlign = TextAlign.Center
			)
		)
	}
}