package com.gproject.schooly.core.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.gproject.schooly.core.utils.ScreenUtil

private val DarkColorScheme = darkColorScheme(
    primary = Palette.primary.color6,
    secondary = Palette.primary.color10,
    error = Palette.red.shade900,
)

private val LightColorScheme = lightColorScheme(
    primary = Palette.primary.color6,
    secondary = Palette.primary.color10,
    error = Palette.red.shade900,


    )

@Composable
fun SchoolyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    ScreenUtil.init()
    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content,
    )
}