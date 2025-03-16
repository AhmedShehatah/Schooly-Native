package com.gproject.schooly.presentation.auth.viewmodel.forgotpassword

import androidx.lifecycle.viewModelScope
import com.gproject.schooly.core.utils.ToastType
import com.gproject.schooly.core.viewmodels.BaseViewModel
import com.gproject.schooly.domain.auth.usecases.CheckOTPParams
import com.gproject.schooly.domain.auth.usecases.CheckOTPUseCase
import com.gproject.schooly.domain.auth.usecases.ForgotPasswordUseCase
import com.gproject.schooly.domain.auth.usecases.ResetPasswordParams
import com.gproject.schooly.domain.auth.usecases.ResetPasswordUseCase
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val checkOTPUseCase: CheckOTPUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : BaseViewModel<ForgotPasswordState, ForgotPasswordEvent, ForgotPasswordEffect>() {
    override fun setInitialState(): ForgotPasswordState = ForgotPasswordState.SendEmail.Initial

    override fun handleEvents(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.SendEmail -> sendEmail(event.email)
            is ForgotPasswordEvent.CheckOTP -> checkOTP(event.email, event.code)
            is ForgotPasswordEvent.ResetPassword -> resetPassword(event.email, event.code)

        }
    }

    private fun sendEmail(email: String) {
        viewModelScope.launch {
            forgotPasswordUseCase(email).launchAndCollect(
                onSuccess = {
                    setEffect {
                        ForgotPasswordEffect.NavigateToOTP
                    }
                    setState { ForgotPasswordState.SendEmail.Success }
                },
                onStart = {
                    setState { ForgotPasswordState.SendEmail.Loading }
                },
                onError = {
                    setState {
                        ForgotPasswordState.SendEmail.Error(it.message ?: "Error")
                    }
                    setEffect {
                        ForgotPasswordEffect.ShowToast(it.message ?: "Error", ToastType.ERROR)
                    }

                },
            )

        }
    }

    private fun checkOTP(email: String, code: String) {
        viewModelScope.launch {
            checkOTPUseCase(CheckOTPParams(email, code)).launchAndCollect(
                onSuccess = {
                    setEffect { ForgotPasswordEffect.NavigateToResetPassword }
                    setState { ForgotPasswordState.CheckOTP.Success }
                },
                onStart = {
                    setState { ForgotPasswordState.CheckOTP.Loading }
                },
                onError = {

                    setState {
                        ForgotPasswordState.CheckOTP.Error(it.message ?: "Error")
                    }

                },
            )

        }
    }


    private fun resetPassword(email: String, code: String) {
        viewModelScope.launch {
            resetPasswordUseCase(ResetPasswordParams(email, code)).launchAndCollect<String?>(
                onSuccess = { body ->
                    setEffect {
                        ForgotPasswordEffect.ShowToast(body ?: "Error", ToastType.ERROR)
                    }
                    setEffect {
                        ForgotPasswordEffect.NavigateToLogin
                    }
                    setState { ForgotPasswordState.ResetPassword.Success }
                },
                onStart = {
                    setState { ForgotPasswordState.ResetPassword.Loading }
                },
                onError = {
                    setState {
                        ForgotPasswordState.ResetPassword.Error(it.message ?: "Error")
                    }
                    setEffect {
                        ForgotPasswordEffect.ShowToast(it.message ?: "Error", ToastType.ERROR)
                    }

                },
            )

        }
    }
}