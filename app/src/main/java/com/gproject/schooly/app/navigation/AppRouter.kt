package com.gproject.schooly.app.navigation

sealed class AppRouter(val route: String) {
    data object SplashScreen : AppRouter("splash_screen")
    data object LoginScreen : AppRouter("login_screen")
}