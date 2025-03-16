package com.gproject.schooly.presentation.auth.pages

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gproject.schooly.R
import com.gproject.schooly.app.navigation.AppRouter
import com.gproject.schooly.core.design.composables.buttons.CustomButton
import com.gproject.schooly.core.design.composables.textfields.OtpInputField
import com.gproject.schooly.core.design.composables.texts.CustomText
import com.gproject.schooly.core.design.composables.texts.TextSize
import com.gproject.schooly.core.design.theme.Palette
import com.gproject.schooly.core.utils.Dimensions
import com.gproject.schooly.core.utils.ToastType
import com.gproject.schooly.core.utils.VerticalSpace
import com.gproject.schooly.core.utils.showToast
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordEffect
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordEvent
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordState
import com.gproject.schooly.presentation.auth.viewmodel.forgotpassword.ForgotPasswordViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckOTPScreen(navController: NavHostController, email: String) {

    val viewModel: ForgotPasswordViewModel = koinViewModel()
    val state = viewModel.viewState.collectAsState().value
    var code by remember {
        mutableStateOf("")
    }


    LaunchedEffect(true) {

        viewModel.effect.onEach {
            when (it) {
                is ForgotPasswordEffect.NavigateToResetPassword -> {
                    navController.navigate(AppRouter.ResetPasswordScreen.route + "/$email")
                }

                is ForgotPasswordEffect.ShowToast -> it.message.showToast(ToastType.ERROR)
                else -> {}
            }
        }.collect()
    }


    Scaffold(
        topBar = {
            Row(Modifier.fillMaxWidth()) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Default.KeyboardArrowLeft, contentDescription = "back")
                }
            }
        },

        ) { padding ->


        Column(
            modifier = Modifier
                .padding(padding)
                .padding(Dimensions.defaultPagePadding)
        ) {
            CustomText(
                text = stringResource(R.string.check_otp),
                size = TextSize.S24,
                bold = true,
            )
            8.VerticalSpace()
            CustomText(
                text = stringResource(R.string.check_otp_sentence),
                size = TextSize.S16,
                center = true,
                color = Palette.character.secondary45
            )
            8.VerticalSpace()

            OtpInputField(
                modifier = Modifier.fillMaxWidth(), onValueChange = { value -> code = value })
            8.VerticalSpace()

            CustomButton(
                text = stringResource(R.string.check_otp),
                isLoading = state is ForgotPasswordState.CheckOTP.Loading,
                onClick = {

                    viewModel.setEvent(ForgotPasswordEvent.CheckOTP(email, code))
                },
            )
        }

    }


}

@Preview
@Composable
fun CheckOTPScreenPreview() {
    CheckOTPScreen(navController = rememberNavController(), email = "")
}