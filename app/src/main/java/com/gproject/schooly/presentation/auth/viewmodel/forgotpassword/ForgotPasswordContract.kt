package com.gproject.schooly.presentation.auth.viewmodel.forgotpassword

import com.gproject.schooly.core.utils.ToastType
import com.gproject.schooly.core.viewmodels.ViewEvent
import com.gproject.schooly.core.viewmodels.ViewSideEffect
import com.gproject.schooly.core.viewmodels.ViewState

sealed class ForgotPasswordState : ViewState {
    sealed class SendEmail {
        object Initial : ForgotPasswordState()
        object Loading : ForgotPasswordState()
        object Success : ForgotPasswordState()
        data class Error(val message: String) : ForgotPasswordState()
    }

    sealed class CheckOTP {
        object Initial : ForgotPasswordState()
        object Loading : ForgotPasswordState()
        object Success : ForgotPasswordState()
        data class Error(val message: String) : ForgotPasswordState()
    }

    sealed class ResetPassword {
        object Loading : ForgotPasswordState()
        object Success : ForgotPasswordState()
        data class Error(val message: String) : ForgotPasswordState()
    }
}

sealed class ForgotPasswordEvent : ViewEvent {
    data class SendEmail(val email: String) : ForgotPasswordEvent()
    data class CheckOTP(val email: String, val code: String) : ForgotPasswordEvent()
    data class ResetPassword(val email: String, val code: String) : ForgotPasswordEvent()

}

sealed class ForgotPasswordEffect : ViewSideEffect {
    data object NavigateToOTP : ForgotPasswordEffect()
    data object NavigateToResetPassword : ForgotPasswordEffect()
    data object NavigateToLogin : ForgotPasswordEffect()
    data class ShowToast(val message: String, val type: ToastType) : ForgotPasswordEffect()
}