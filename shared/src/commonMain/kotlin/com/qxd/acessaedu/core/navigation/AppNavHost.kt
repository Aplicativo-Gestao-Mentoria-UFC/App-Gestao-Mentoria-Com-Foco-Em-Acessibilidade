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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.read
import com.qxd.acessaedu.core.presentation.components.MainTab
import com.qxd.acessaedu.features.auth.presentation.CreateAccountScreen
import com.qxd.acessaedu.features.auth.presentation.ForgotPasswordScreen
import com.qxd.acessaedu.features.auth.presentation.LoginScreen
import com.qxd.acessaedu.features.auth.presentation.NewPasswordScreen
import com.qxd.acessaedu.features.auth.presentation.VerifyCodeScreen
import com.qxd.acessaedu.features.calendar.presentation.CalendarScreen
import com.qxd.acessaedu.features.classes.presentation.EnterClassCodeScreen
import com.qxd.acessaedu.features.classes.presentation.TurmasScreen
import com.qxd.acessaedu.features.home.presentation.HomeScreen
import com.qxd.acessaedu.features.profile.presentation.ProfileScreen
import com.qxd.acessaedu.features.classes.presentation.ClassDetailsScreen
import com.qxd.acessaedu.ui.theme.DefaultColors

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
                    onLoginClick = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
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
                ForgotPasswordScreen(
                    emailAddress = "email@exemplo.com",
                    phoneNumber = "(85) 99999-9999",
                    onBack = {
                        navController.popBackStack()
                    },
                    onContinue = {
                        navController.navigate("${Routes.VERIFY_CODE}/recuperacao@email.com")
                    }
                )
            }

            composable(Routes.NEW_PASSWORD) {
                NewPasswordScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onContinue = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
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

            composable(Routes.HOME) {
                HomeScreen(
                    onTabClick = { tab ->
                        navController.navigateToMainTab(tab)
                    }
                )
            }

            composable(Routes.TURMAS) {
                TurmasScreen(
                    onTabClick = { tab ->
                        navController.navigateToMainTab(tab)
                    },
                    onAddClassClick = {
                        navController.navigate(Routes.ENTER_CLASS_CODE) {
                            launchSingleTop = true
                        }
                    },
                    onClassDetailsClick = {
                        navController.navigate(Routes.CLASS_DETAILS)
                    }
                )
            }

            composable(Routes.ENTER_CLASS_CODE) {
                EnterClassCodeScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEnterClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Routes.CLASS_DETAILS) {
                ClassDetailsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Routes.CALENDARIO) {
                CalendarScreen(
                    onTabClick = { tab ->
                        navController.navigateToMainTab(tab)
                    }
                )
            }

            composable(Routes.PERFIL) {
                ProfileScreen(
                    onTabClick = { tab ->
                        navController.navigateToMainTab(tab)
                    },
                    onLogoutClick = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

private fun NavController.navigateToMainTab(tab: MainTab) {
    val destination = when (tab) {
        MainTab.HOME -> Routes.HOME
        MainTab.TURMAS -> Routes.TURMAS
        MainTab.CALENDARIO -> Routes.CALENDARIO
        MainTab.MATERIAIS -> null
        MainTab.PERFIL -> Routes.PERFIL
    }

    destination?.let { route ->
        navigate(route) {
            launchSingleTop = true
        }
    }
}