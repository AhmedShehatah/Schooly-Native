package com.gproject.schooly.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gproject.schooly.core.animation.defaultEnterTransition
import com.gproject.schooly.core.animation.defaultExitTransition
import com.gproject.schooly.core.animation.defaultPopEnterTransition
import com.gproject.schooly.core.animation.defaultPopExitTransition
import com.gproject.schooly.presentation.auth.pages.CheckOTPScreen
import com.gproject.schooly.presentation.auth.pages.EmailScreen
import com.gproject.schooly.presentation.auth.pages.LoginScreen
import com.gproject.schooly.presentation.auth.pages.ResetPasswordScreen
import com.gproject.schooly.presentation.splash.SplashScreen

@Composable
fun SchoolyNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { defaultEnterTransition() },
        exitTransition = { defaultExitTransition() },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
    ) {
        composable(route = AppRouter.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = AppRouter.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AppRouter.EmailScreen.route) {
            EmailScreen(navController = navController)
        }
        composable(route = AppRouter.OTPScreen.route + "/{email}") {
            val email = it.arguments?.getString("email")
            CheckOTPScreen(navController = navController, email = email ?: "")
        }
        composable(route = AppRouter.ResetPasswordScreen.route + "/{email}") {
            val email = it.arguments?.getString("email")
            ResetPasswordScreen(navController = navController, email = email ?: "")
        }

    }
}




