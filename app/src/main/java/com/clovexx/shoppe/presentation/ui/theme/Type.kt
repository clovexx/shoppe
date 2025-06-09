package com.clovexx.shoppe.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.clovexx.shoppe.R


val NunitoSansFontFamily = FontFamily(
    Font(R.font.nunitosans)
)

val RalewayFontFamily = FontFamily(
    Font(R.font.raleway)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = RalewayFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        lineHeight = 54.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = RalewayFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.28).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RalewayFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 21.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.21).sp
    ),
    titleLarge = TextStyle(
        fontFamily = RalewayFontFamily,
        fontWeight = FontWeight.W800,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.2).sp
    ),
    titleMedium = TextStyle(
        fontFamily = RalewayFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.17).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        lineHeight = 19.sp
    ),
    labelLarge = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 13.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )
)
