package com.gproject.schooly.core.animation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset

private val defaultTween = tween<IntOffset>(durationMillis = 300)

// Generic slide transitions
fun slideInRight() = slideInHorizontally(animationSpec = defaultTween) { fullWidth -> fullWidth }
fun slideOutLeft() = slideOutHorizontally(animationSpec = defaultTween) { fullWidth -> -fullWidth }
fun slideInLeft() = slideInHorizontally(animationSpec = defaultTween) { fullWidth -> -fullWidth }
fun slideOutRight() = slideOutHorizontally(animationSpec = defaultTween) { fullWidth -> fullWidth }

// Optional: Add fade for smoothness
private val defaultFloatTween = tween<Float>(durationMillis = 300)
fun fadeInTransition() = fadeIn(animationSpec = defaultFloatTween)
fun fadeOutTransition() = fadeOut(animationSpec = defaultFloatTween)

// Combine slide + fade
fun defaultEnterTransition() = slideInRight() + fadeInTransition()
fun defaultExitTransition() = slideOutLeft() + fadeOutTransition()
fun defaultPopEnterTransition() = slideInLeft() + fadeInTransition()
fun defaultPopExitTransition() = slideOutRight() + fadeOutTransition()