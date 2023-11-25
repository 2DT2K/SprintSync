package com.sprintsync.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.sprintsync.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val poppins = GoogleFont("Poppins")
val openSans = GoogleFont("Open Sans")

val fontPoppins = FontFamily(
    Font(googleFont = poppins, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = poppins, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = poppins, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = poppins, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = poppins, fontProvider = provider, weight = FontWeight.ExtraBold),
)

val fontOpenSans = FontFamily(
    Font(googleFont = openSans, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = openSans, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = openSans, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = openSans, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = openSans, fontProvider = provider, weight = FontWeight.ExtraBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.4.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 19.2.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = fontOpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 20.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = fontPoppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
    )
)
