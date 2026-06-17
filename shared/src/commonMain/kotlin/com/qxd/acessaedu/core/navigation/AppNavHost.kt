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
import com.qxd.acessaedu.features.auth.presentation.LoginScreen
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

            composable(
                route = "${Routes.VERIFY_CODE}/{email}",
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val email = backStackEntry.arguments
                    ?.read { getString("email") }
                    .orEmpty()

                VerifyCodeScreen(
                    email = email,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}