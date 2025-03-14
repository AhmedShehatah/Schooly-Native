package com.gproject.schooly.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gproject.schooly.R
import com.gproject.schooly.app.navigation.AppRouter
import com.gproject.schooly.core.design.composables.buttons.CustomButton
import com.gproject.schooly.core.design.composables.texts.CustomText
import com.gproject.schooly.core.design.composables.texts.TextSize
import com.gproject.schooly.core.design.theme.Palette
import com.gproject.schooly.core.utils.VerticalSpace
import com.gproject.schooly.core.utils.h
import com.gproject.schooly.core.utils.w
import com.gproject.schooly.core.viewmodels.SideEffectsKey
import com.gproject.schooly.presentation.splash.viewmodel.SplashEffect
import com.gproject.schooly.presentation.splash.viewmodel.SplashEvents
import com.gproject.schooly.presentation.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun SplashScreen(navController: NavController) {

    val viewmodel: SplashViewModel = koinViewModel()




    LaunchedEffect(SideEffectsKey) {
        viewmodel.setEvent(SplashEvents.CheckFirstTime)

        viewmodel.effect.onEach {
            when (it) {
                is SplashEffect.NavigateToLogin -> {
                    navController.navigate(AppRouter.LoginScreen.route)
                    Timber.i("Navigate to login")
                }
            }
        }.collect {}
    }




    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 18.w(), vertical = 18.h()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Image(
            modifier = Modifier.scale(1.5f),
            painter = painterResource(id = R.drawable.splash), contentDescription = "Splash",
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomText(
                text = stringResource(R.string.your_school),
                size = TextSize.S30,
                bold = true,
                color = Palette.primary.color8

            )
            CustomText(
                text = stringResource(R.string.in_your_pocket),
                size = TextSize.S46,
                bold = true,
                color = Palette.character.primary85,
            )
            12.VerticalSpace()
            CustomText(
                text = stringResource(R.string.splash_setence), size = TextSize.S14,
                center = true,
                color = Palette.character.secondary45,
            )
        }

        CustomButton(text = stringResource(R.string.start_now), onClick = {
            viewmodel.setEvent(SplashEvents.NavigateToLogin)
        })
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}