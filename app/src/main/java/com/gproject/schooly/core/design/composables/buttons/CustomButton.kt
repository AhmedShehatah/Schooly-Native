package com.gproject.schooly.core.design.composables.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gproject.schooly.core.design.theme.Palette

enum class IconAlignment {
    Start, End
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = true,
    isLoading: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
    underlineText: Boolean = false,
    backgroundColor: Color? = null,
    isTextBold: Boolean = false,
    borderRadius: Float? = null,
    enabled: Boolean = true,
    textColor: Color? = null,
    isRed: Boolean = false,
    iconAlignment: IconAlignment = IconAlignment.End,
    isOutlined: Boolean = false,
    isText: Boolean = false
) {
    val buttonModifier = if (isExpanded) {
        modifier.fillMaxWidth()
    } else {
        modifier
    }

    // Determine text and button colors
    val resolvedTextColor = textColor ?: when {
        isOutlined -> if (isRed) Palette.red.shade700 else Palette.primary.color6
        isText -> if (isRed) Palette.red.shade700 else Palette.primary.color6
        else -> Color.White
    }
    val resolvedButtonColor =
        backgroundColor ?: if (isRed) Palette.red.shade700 else Palette.primary.color6

    // Button content
    val content: @Composable RowScope.() -> Unit = {
        if (isLoading) {
            CircularProgressIndicator(
                color = resolvedTextColor, strokeWidth = 2.dp, modifier = Modifier.size(24.dp)
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (iconAlignment == IconAlignment.Start) Arrangement.Start else Arrangement.End
            ) {
                if (icon != null && iconAlignment == IconAlignment.Start) {
                    icon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    fontSize = 16.sp,
                    color = resolvedTextColor,
                    fontWeight = if (isTextBold) FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if (underlineText) TextDecoration.Underline else null,
                    maxLines = 1
                )
                if (icon != null && iconAlignment == IconAlignment.End) {
                    Spacer(modifier = Modifier.width(8.dp))
                    icon()
                }
            }
        }
    }

    // Render the appropriate button type
    when {
        isOutlined -> {
            OutlinedButton(
                onClick = if (enabled && !isLoading) onClick else ({}),
                modifier = buttonModifier,
                border = BorderStroke(1.dp, resolvedButtonColor),
                shape = if (borderRadius != null) RoundedCornerShape(borderRadius.dp) else MaterialTheme.shapes.small,
                enabled = enabled,
                content = content
            )
        }

        isText -> {
            TextButton(
                onClick = if (enabled && !isLoading) onClick else ({}),
                modifier = buttonModifier,
                enabled = enabled,
                content = content
            )
        }

        else -> {
            Button(
                onClick = if (enabled && !isLoading) onClick else ({}),
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = resolvedButtonColor,
                    disabledContainerColor = Color.Gray // Default disabled color
                ),
                shape = if (borderRadius != null) RoundedCornerShape(borderRadius.dp) else MaterialTheme.shapes.small,
                enabled = enabled,
                content = content
            )
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(
        text = "Login",
        onClick = {},

        )
}