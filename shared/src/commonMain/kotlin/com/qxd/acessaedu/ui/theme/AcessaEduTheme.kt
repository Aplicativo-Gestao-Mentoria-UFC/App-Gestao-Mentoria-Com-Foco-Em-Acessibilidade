package com.qxd.acessaedu.ui.theme

import acessaedu.shared.generated.resources.Jost_Bold
import acessaedu.shared.generated.resources.Jost_Medium
import acessaedu.shared.generated.resources.Jost_Regular
import acessaedu.shared.generated.resources.Jost_SemiBold
import acessaedu.shared.generated.resources.Res
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font


val DarkBlue = Color(0xFF061A3B)
val DarkBlue2 = Color(0xFF082148)
val PrimaryBlue = Color(0xFF0E55D8)
val EduGreen = Color(0xFF7CC242)
val TextDark = Color(0xFF1C2144)
val TextMuted = Color(0xFF777A86)
val ErrorRed = Color(0xFFFF2D45)
val LineGray = Color(0xFFD4D4D4)

private val AcessaEduColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = EduGreen,
    background = Color.White,
    surface = Color.White,
    error = ErrorRed,
    onPrimary = Color.White,
    onBackground = TextDark,
    onSurface = TextDark,
    onError = Color.White
)

@Composable
private fun AcessaEduFontFamily(): FontFamily {
    return FontFamily(
        Font(Res.font.Jost_Regular, weight = FontWeight.Normal),
        Font(Res.font.Jost_Medium, weight = FontWeight.Medium),
        Font(Res.font.Jost_SemiBold, weight = FontWeight.SemiBold),
        Font(Res.font.Jost_Bold, weight = FontWeight.Bold)
    )
}

@Composable
private fun AcessaEduTypography(): Typography {
    val fontFamily = AcessaEduFontFamily()

    return Typography(
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        ),
        titleMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    )
}

@Composable
fun AcessaEduTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AcessaEduColorScheme,
        typography = AcessaEduTypography(),
        content = content
    )
}