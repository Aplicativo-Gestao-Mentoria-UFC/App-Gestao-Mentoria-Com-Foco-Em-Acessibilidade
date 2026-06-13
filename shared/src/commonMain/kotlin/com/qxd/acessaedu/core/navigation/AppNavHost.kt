package com.qxd.acessaedu.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.qxd.acessaedu.AuthScreen
import com.qxd.acessaedu.features.auth.presentation.CreateAccountScreen
import com.qxd.acessaedu.features.auth.presentation.LoginScreen
import com.qxd.acessaedu.features.auth.presentation.VerifyCodeScreen

@Composable
fun AppNavHost() {
    var screen by remember { mutableStateOf<AuthScreen>(AuthScreen.Login) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        when (val currentScreen = screen) {
            AuthScreen.Login -> LoginScreen(
                onCreateAccountClick = {
                    screen = AuthScreen.CreateAccount
                }
            )

            AuthScreen.CreateAccount -> CreateAccountScreen(
                onBackToLogin = {
                    screen = AuthScreen.Login
                },
                onCodeSent = { email ->
                    screen = AuthScreen.VerifyCode(email)
                }
            )

            is AuthScreen.VerifyCode -> VerifyCodeScreen(
                email = currentScreen.email,
                onBack = {
                    screen = AuthScreen.CreateAccount
                }
            )
        }
    }
}