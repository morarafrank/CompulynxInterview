package com.morarafrank.compulynxinterview.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.R

val fontFamily = FontFamily(
    Font(R.font.dm_sans_regular),
    Font(R.font.dm_sans_bold),
    Font(R.font.dm_sans_medium),
    Font(R.font.dm_sans_light),
    Font(R.font.dm_sans_semibold),
)
val boldFont = FontFamily(Font(R.font.dm_sans_bold))
val regularFont = FontFamily(Font(R.font.dm_sans_regular))
val mediumFont = FontFamily(Font(R.font.dm_sans_medium))
val smallFont = FontFamily(Font(R.font.dm_sans_light))

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = regularFont,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = regularFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)