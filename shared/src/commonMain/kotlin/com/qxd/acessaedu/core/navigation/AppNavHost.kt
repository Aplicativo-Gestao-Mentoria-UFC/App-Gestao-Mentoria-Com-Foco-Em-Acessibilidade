package com.qxd.acessaedu.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.qxd.acessaedu.features.auth.presentation.CreateAccountScreen
import com.qxd.acessaedu.features.auth.presentation.ForgotPasswordScreen
import com.qxd.acessaedu.features.auth.presentation.LoginScreen
import com.qxd.acessaedu.features.auth.presentation.NewPasswordScreen
import com.qxd.acessaedu.features.auth.presentation.RecoveryMethod
import com.qxd.acessaedu.features.auth.presentation.VerifyCodeScreen
import com.qxd.acessaedu.ui.theme.DefaultColors
import androidx.savedstate.read

private const val NAV_ANIMATION_DURATION = 280

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultColors.DarkBlue)
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.LOGIN,
            modifier = Modifier.fillMaxSize(),
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION),
                    initialOffsetX = { fullWidth -> fullWidth }
                ) + fadeIn(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION),
                    targetOffsetX = { fullWidth -> -fullWidth }
                ) + fadeOut(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION),
                    initialOffsetX = { fullWidth -> -fullWidth }
                ) + fadeIn(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION),
                    targetOffsetX = { fullWidth -> fullWidth }
                ) + fadeOut(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                )
            }
        ) {
            composable(Routes.LOGIN) {
                LoginScreen(
                    onCreateAccountClick = {
                        navController.navigate(Routes.CREATE_ACCOUNT)
                    },
                    onForgotPasswordClick = {
                        navController.navigate(Routes.FORGOT_PASSWORD)
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

            composable(Routes.FORGOT_PASSWORD) {
                val emailAddress = "alunomonitor@alu.ufc.br"
                val phoneNumber = "(+91) 958-894-5529"

                ForgotPasswordScreen(
                    emailAddress = emailAddress,
                    phoneNumber = phoneNumber,
                    onBack = {
                        navController.popBackStack()
                    },
                    onContinue = { method ->
                        val contact = when (method) {
                            RecoveryMethod.EMAIL -> emailAddress
                            RecoveryMethod.SMS -> phoneNumber
                        }
                        navController.navigate("${Routes.VERIFY_CODE}/$contact?purpose=forgot_password")
                    }
                )
            }

            composable(
                route = "${Routes.VERIFY_CODE}/{contact}?purpose={purpose}",
                arguments = listOf(
                    navArgument("contact") {
                        type = NavType.StringType
                    },
                    navArgument("purpose") {
                        type = NavType.StringType
                        defaultValue = "signup"
                    }
                )
            ) { backStackEntry ->
                val contact = backStackEntry.arguments
                    ?.read { getString("contact") }
                    .orEmpty()

                val purpose = backStackEntry.arguments
                    ?.read { getString("purpose") }
                    .orEmpty()

                val isForgotPassword = purpose == "forgot_password"

                VerifyCodeScreen(
                    title = if (isForgotPassword) "Esqueci minha senha" else "Confirmar cadastro",
                    description = if (isForgotPassword) {
                        "O código foi enviado para $contact"
                    } else {
                        "Digite o código enviado para $contact."
                    },
                    onBack = {
                        navController.popBackStack()
                    },
                    onVerify = { code, onError ->
                        if (isForgotPassword) {
                            navController.navigate(Routes.NEW_PASSWORD)
                        } else {
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.LOGIN) { inclusive = true }
                            }
                        }
                    }
                )
            }

            composable(Routes.NEW_PASSWORD) {
                NewPasswordScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onContinue = { newPassword ->
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}