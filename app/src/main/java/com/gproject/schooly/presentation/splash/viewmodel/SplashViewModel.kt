package com.gproject.schooly.presentation.splash.viewmodel

import androidx.lifecycle.viewModelScope
import com.gproject.schooly.core.preferences.preferenceManager.PreferencesManager
import com.gproject.schooly.core.viewmodels.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(private val preferenceManager: PreferencesManager) :
    BaseViewModel<SplashState, SplashEvents, SplashEffect>() {
    override fun setInitialState(): SplashState = SplashState.Initial

    override fun handleEvents(event: SplashEvents) {
        when (event) {
            is SplashEvents.NavigateToLogin -> {
                viewModelScope.launch {
                    preferenceManager.saveUnEncrypted("isFirstTime", false)

                }
            }

            is SplashEvents.CheckFirstTime -> {
                checkFirstTime()
            }
        }
    }


    private fun checkFirstTime() {
        viewModelScope.launch {
            preferenceManager.getUnEncrypted("isFirstTime", true).collect {
                if (!it) setEffect { SplashEffect.NavigateToLogin }
            }

        }
    }
}