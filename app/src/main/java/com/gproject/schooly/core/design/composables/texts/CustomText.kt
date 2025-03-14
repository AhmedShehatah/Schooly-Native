package com.gproject.schooly.core.design.composables.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.gproject.schooly.R
import com.gproject.schooly.core.utils.sp

enum class TextSize(val value: Float) {
    S7(7f), S8(8f), S9(9f), S10(10f), S11(11f), S12(12f), S13(13f), S14(14f), S15(15f), S16(16f), S17(
        17f
    ),
    S18(18f), S20(20f), S22(22f), S23(23f), S24(24f), S27(27f), S30(30f), S33(33f), S46(46f), S48(
        48f
    ),
}

@Composable
fun CustomText(
    text: String?,
    size: TextSize,
    center: Boolean = false,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: Boolean = false,
    bold: Boolean = false,
    underline: Boolean = false,
) {
    Text(
        text ?: "N/A",
        color = color,
        fontFamily = FontFamily(Font(R.font.alex)),
        fontSize = size.value.sp(), // Responsive size using ScreenUtil
        fontWeight = if (bold) FontWeight.Bold else null,
        textAlign = if (center) TextAlign.Center else null,
        overflow = if (overflow) TextOverflow.Ellipsis else TextOverflow.Clip,
        maxLines = maxLines,
        textDecoration = if (underline) TextDecoration.Underline else null
    )
}

