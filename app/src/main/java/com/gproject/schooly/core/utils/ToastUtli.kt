package com.gproject.schooly.core.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.gproject.schooly.core.animation.slideInRight
import com.gproject.schooly.core.animation.slideOutLeft
import com.gproject.schooly.core.design.composables.texts.CustomText
import com.gproject.schooly.core.design.composables.texts.TextSize
import com.gproject.schooly.core.design.theme.Palette
import kotlinx.coroutines.delay

enum class ToastType {
    SUCCESS, ERROR, INFO
}

fun String.showToast(
    type: ToastType
) {
    // We'll need a way to show this globally, so we'll use a composable launcher
    ToastLauncher.launchToast(
        message = this, type = type
    )
}

// Toast Composable
@Composable
fun ToastMessage(
    message: String, type: ToastType, onDismiss: () -> Unit, modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }

    AnimatedVisibility(
        modifier = Modifier.padding(Dimensions.defaultPagePadding),
        visible = true,
        enter = slideInRight(),
        exit = slideOutLeft()
    ) {
        LaunchedEffect(Unit) {
            delay(3000) // Auto-close after 3 seconds
            onDismiss()
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    when (type) {
                        ToastType.SUCCESS -> Palette.primary.color6 // Green
                        ToastType.ERROR -> Palette.red.shade600  // Red
                        ToastType.INFO -> Palette.lightBlue.shade300   // Blue
                    }
                )
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        offsetX += dragAmount
                        if (kotlin.math.abs(offsetX) > 100) {
                            onDismiss()
                        }
                    }
                }
                .padding(16.dp)) {

            CustomText(
                text = message, color = Color.White, size = TextSize.S18,
            )
        }
    }
}

// Toast Launcher to manage showing toasts globally
object ToastLauncher {
    private var currentToast by mutableStateOf<Pair<String, ToastType>?>(null)

    fun launchToast(message: String, type: ToastType) {
        currentToast = Pair(message, type)
    }

    @Composable
    fun ToastHost(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
        Scaffold { padding ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {

                content()
                currentToast?.let { (message, type) ->
                    ToastMessage(
                        message = message,
                        type = type,
                        onDismiss = { currentToast = null },
                        modifier = modifier
                            .align(Alignment.TopCenter)
                            .padding()
                    )

                }


            }
        }

    }
}