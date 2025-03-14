package com.gproject.schooly.presentation.auth.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.gproject.schooly.core.design.composables.buttons.CustomButton
import com.gproject.schooly.core.design.composables.textfields.CustomTextField
import com.gproject.schooly.core.utils.VerticalSpace
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
                is LoginSideEffect.ShowToast -> Toast.makeText(
                    context, effect.message, Toast.LENGTH_SHORT
                ).show()

            }
        }.collect()
    }


    var email by remember {
        mutableStateOf("test@test.com")
    }
    var password by remember {
        mutableStateOf("testtest")
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CustomTextField(
            value = email,
            onValueChange = { value ->
                email = value
            },
            label = "email",
        )
        12.VerticalSpace()
        CustomTextField(
            value = password,
            onValueChange = { value ->
                password = value
            },
            label = "password",
            isSecure = true,
        )

        CustomButton(
            text = "Login",
            isLoading = state == LoginViewState.Loading,
            onClick = {
                viewmodel.setEvent(
                    LoginViewEvent.LoginUser(

                        email = email, password = password

                    )
                )
            },
        )


    }


}

