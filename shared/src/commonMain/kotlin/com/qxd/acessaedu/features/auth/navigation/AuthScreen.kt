package com.qxd.acessaedu.features.auth.navigation
sealed interface AuthScreen {
    data object Login : AuthScreen
    data object CreateAccount : AuthScreen
    data class VerifyCode(val email: String) : AuthScreen
}