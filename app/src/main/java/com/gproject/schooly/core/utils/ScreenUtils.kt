package com.gproject.schooly.core.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ScreenUtil {
    internal var designWidth = 393f // Reference design width in dp
    internal var designHeight = 852f // Reference design height in dp

    fun init(designWidthDp: Float = 393f, designHeightDp: Float = 852f) {
        designWidth = designWidthDp
        designHeight = designHeightDp
    }
}

// Extension functions for Float
@Composable
fun Float.w(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.toFloat()
    return (this * screenWidthDp / ScreenUtil.designWidth).dp
}

@Composable
fun Float.h(): Dp {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.toFloat()
    return (this * screenHeightDp / ScreenUtil.designHeight).dp
}

@Composable
fun Float.r(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.toFloat()
    val screenHeightDp = configuration.screenHeightDp.toFloat()
    val scale =
        minOf(screenWidthDp / ScreenUtil.designWidth, screenHeightDp / ScreenUtil.designHeight)
    return (this * scale).dp
}

@Composable
fun Float.sp(): TextUnit {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.toFloat()
    val scale = screenWidthDp / ScreenUtil.designWidth
    return (this * scale).sp
}

// Extension functions for Int (optional, if you prefer whole numbers)
@Composable
fun Int.w(): Dp = this.toFloat().w()

@Composable
fun Int.h(): Dp = this.toFloat().h()

@Composable
fun Int.r(): Dp = this.toFloat().r()

@Composable
fun Int.sp(): TextUnit = this.toFloat().sp()

@Composable
fun Int.VerticalSpace() = Box(modifier = Modifier.height(this.h()))

@Composable
fun Int.HorizontalSpace() = Box(modifier = Modifier.width(this.w()))