package com.gproject.schooly.presentation.auth.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gproject.schooly.R
import com.gproject.schooly.app.navigation.AppRouter
import com.gproject.schooly.core.design.composables.buttons.CustomButton
import com.gproject.schooly.core.design.composables.textfields.CustomTextField
import com.gproject.schooly.core.design.composables.texts.CustomText
import com.gproject.schooly.core.design.composables.texts.TextSize
import com.gproject.schooly.core.design.theme.Palette
import com.gproject.schooly.core.utils.Dimensions
import com.gproject.schooly.core.utils.ToastType
import com.gproject.schooly.core.utils.VerticalSpace
import com.gproject.schooly.core.utils.showToast
import com.gproject.schooly.core.viewmodels.SideEffectsKey
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordEffect
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordEvent
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordState
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmailScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }


    val viewModel: ForgotPasswordViewModel = koinViewModel()

    val state = viewModel.viewState.collectAsState().value

    LaunchedEffect(SideEffectsKey) {

        viewModel.effect.onEach {
            when (it) {

                is ForgotPasswordEffect.NavigateToOTP -> {
                    navController.navigate(AppRouter.OTPScreen.route + "/$email")
                }
                is ForgotPasswordEffect.ShowToast -> it.message.showToast(ToastType.ERROR)

                else -> {}
            }
        }.collect()

    }



    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
            ) {
                IconButton(

                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(
                        Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "back",
                    )
                }
            }
        }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(Dimensions.defaultPagePadding)
        ) {

            CustomText(
                text = stringResource(R.string.change_password), size = TextSize.S24, bold = true
            )

            8.VerticalSpace()

            CustomTextField(
                label = stringResource(id = R.string.email),
                onValueChange = { value -> email = value },
                value = email
            )
            8.VerticalSpace()

            CustomButton(
                text = stringResource(R.string.send_otp),
                isLoading = state == ForgotPasswordState.SendEmail.Loading,
                onClick = {

                    viewModel.setEvent(ForgotPasswordEvent.SendEmail(email))
                },
            )
            CustomButton(
                text = stringResource(R.string.back_to_login),
                onClick = {
                    navController.popBackStack()
                },
                isText = true,
                textColor = Palette.character.secondary45,
            )
        }
    }

}

@Preview
@Composable
fun EmailScreenPreview() {
    EmailScreen(rememberNavController())
}