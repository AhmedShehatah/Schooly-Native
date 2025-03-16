package com.gproject.schooly.core.design.theme

import android.app.Activity
import android.view.Window
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
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
    val view = LocalView.current
    val window: Window = (view.context as Activity).window
    SideEffect {
        window.statusBarColor = Palette.primary.color6.toArgb()
    }
    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content,

        )
}