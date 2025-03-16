package com.gproject.schooly.presentation.auth.viewmodel.login

import com.gproject.schooly.core.utils.ToastType
import com.gproject.schooly.core.viewmodels.ViewEvent
import com.gproject.schooly.core.viewmodels.ViewSideEffect
import com.gproject.schooly.core.viewmodels.ViewState

sealed class LoginViewEvent : ViewEvent {
    data class LoginUser(val email: String, val password: String) : LoginViewEvent()
}

sealed class LoginViewState : ViewState {
    data object Initial : LoginViewState()
    data object Loading : LoginViewState()
    data object Success : LoginViewState()
    data class Error(val message: String) : LoginViewState()
}

sealed class LoginSideEffect : ViewSideEffect {
    data class ShowToast(val message: String, val type: ToastType) : LoginSideEffect()
}