package com.gproject.schooly.presentation.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.gproject.schooly.core.viewmodels.BaseViewModel
import com.gproject.schooly.domain.auth.usecases.LoginParams
import com.gproject.schooly.domain.auth.usecases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginViewState, LoginViewEvent, LoginSideEffect>() {
    override fun setInitialState(): LoginViewState = LoginViewState.Initial


    override fun handleEvents(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.LoginUser -> signIn(event.email, event.password)
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(LoginParams(email = email, password = password)).launchAndCollect(
                onSuccess = {
                    setEffect { LoginSideEffect.ShowToast("Success") }
                    setState { LoginViewState.Success }
                },
                onStart = {
                    setState { LoginViewState.Loading }
                },
                onError = {
                    setEffect { LoginSideEffect.ShowToast(it.message ?: "Error") }
                    setState {
                        LoginViewState.Error(it.message ?: "Error")
                    }

                },
            )
        }
    }

}




