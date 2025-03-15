package com.gproject.schooly.presentation.auth.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.gproject.schooly.R
import com.gproject.schooly.core.design.composables.buttons.CustomButton
import com.gproject.schooly.core.design.composables.textfields.CustomTextField
import com.gproject.schooly.core.design.theme.Palette
import com.gproject.schooly.core.utils.Dimensions
import com.gproject.schooly.core.utils.ToastType
import com.gproject.schooly.core.utils.VerticalSpace
import com.gproject.schooly.core.utils.showToast
import com.gproject.schooly.core.viewmodels.SideEffectsKey
import com.gproject.schooly.presentation.auth.viewmodel.LoginSideEffect
import com.gproject.schooly.presentation.auth.viewmodel.LoginViewEvent
import com.gproject.schooly.presentation.auth.viewmodel.LoginViewModel
import com.gproject.schooly.presentation.auth.viewmodel.LoginViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewmodel = koinViewModel<LoginViewModel>()
    val state = viewmodel.viewState.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(SideEffectsKey) {
        viewmodel.effect.onEach { effect ->

            when (effect) {
                is LoginSideEffect.ShowToast -> effect.message.showToast(ToastType.SUCCESS)

            }
        }.collect()
    }


    var email by remember {
        mutableStateOf("test@test.com")
    }
    var password by remember {
        mutableStateOf("testtest")
    }

    Scaffold { innerPadding ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(Dimensions.defaultPagePadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                modifier = Modifier.scale(1.2f),
                painter = painterResource(R.drawable.login),
                contentDescription = "login",
            )
            Column {
                CustomTextField(
                    value = email,
                    onValueChange = { value ->
                        email = value
                    },
                    label = stringResource(R.string.email),
                )
                12.VerticalSpace()
                CustomTextField(
                    value = password,
                    onValueChange = { value ->
                        password = value
                    },
                    label = stringResource(R.string.password),
                    isSecure = true,
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CustomButton(
                    text = stringResource(R.string.login),
                    isLoading = state == LoginViewState.Loading,
                    onClick = {
                        viewmodel.setEvent(
                            LoginViewEvent.LoginUser(
                                email = email, password = password
                            )
                        )
                    },
                )
                CustomButton(
                    text = stringResource(R.string.forgot_password),
                    isText = true,
                    textColor = Palette.character.secondary45,
                    onClick = {
                        "test".showToast(ToastType.SUCCESS)
                    },
                )

            }


        }
    }


}

