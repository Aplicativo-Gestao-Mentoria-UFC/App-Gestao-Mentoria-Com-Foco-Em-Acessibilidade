package com.qxd.acessaedu.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qxd.acessaedu.features.auth.presentation.CreateAccountScreen
import com.qxd.acessaedu.features.auth.presentation.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onCreateAccountClick = {
                    navController.navigate(Routes.CREATE_ACCOUNT)
                }
            )
        }

        composable(Routes.CREATE_ACCOUNT) {
            CreateAccountScreen(
                onBackToLogin = {
                    navController.popBackStack()
                },
                onCodeSent = { email ->
                    navController.navigate("${Routes.VERIFY_CODE}/$email")
                }
            )
        }
    }
}