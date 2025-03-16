package com.gproject.schooly.app.navigation

sealed class AppRouter(val route: String) {
    data object SplashScreen : AppRouter("splash_screen")
    data object LoginScreen : AppRouter("login_screen")
    data object EmailScreen : AppRouter("email_screen")
    data object OTPScreen : AppRouter("otp_screen")
    data object ResetPasswordScreen : AppRouter("reset_password_screen")
}