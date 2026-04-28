package com.kezlab.imagecalendar.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppColors {
    val Bg = Color(0xFFFAFAF7)
    val Bg2 = Color(0xFFF3F0EB)
    val Card = Color(0xFFFFFFFF)
    val Border = Color(0xFFE8E4DD)
    val Text = Color(0xFF2C2825)
    val Text2 = Color(0xFF7A746C)
    val Text3 = Color(0xFFB0A99F)
    val Accent = Color(0xFF7C6FC4)
    val AccentLight = Color(0xFFEAE7F8)
    val Good = Color(0xFF6BAE8C)
    val GoodLight = Color(0xFFE4F2EB)
    val Danger = Color(0xFFD94F3D)
}

private val ImageCalendarColorScheme = lightColorScheme(
    primary = AppColors.Accent,
    onPrimary = Color.White,
    secondary = AppColors.Good,
    background = AppColors.Bg,
    onBackground = AppColors.Text,
    surface = AppColors.Card,
    onSurface = AppColors.Text,
    error = AppColors.Danger,
)

@Composable
fun ImageCalendarTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ImageCalendarColorScheme,
        content = content,
    )
}
