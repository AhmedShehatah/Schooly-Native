package com.gproject.schooly.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gproject.schooly.app.navigation.AppRouter
import com.gproject.schooly.app.navigation.SchoolyNavHost
import com.gproject.schooly.core.design.theme.SchoolyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        SchoolyNavHost(
                            navController = rememberNavController(),
                            startDestination = AppRouter.SplashScreen.route
                        )
                    }

                }
            }

        }
    }
}

