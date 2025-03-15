package com.gproject.schooly.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.gproject.schooly.app.navigation.AppRouter
import com.gproject.schooly.app.navigation.SchoolyNavHost
import com.gproject.schooly.core.design.theme.SchoolyTheme
import com.gproject.schooly.core.utils.ToastLauncher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()
            SchoolyTheme {
                ToastLauncher.ToastHost {
                    SchoolyNavHost(
                        navController = navController,
                        startDestination = AppRouter.SplashScreen.route
                    )
                }

            }
        }

    }
}


