package com.gproject.schooly.presentation.splash.viewmodel

import com.gproject.schooly.core.viewmodels.ViewEvent
import com.gproject.schooly.core.viewmodels.ViewSideEffect
import com.gproject.schooly.core.viewmodels.ViewState

sealed class SplashState : ViewState {
    data object Initial : SplashState()
}

sealed class SplashEvents : ViewEvent {
    data object NavigateToLogin : SplashEvents()
    data object CheckFirstTime : SplashEvents()

}

sealed class SplashEffect : ViewSideEffect {
    data object NavigateToLogin : SplashEffect()
}
