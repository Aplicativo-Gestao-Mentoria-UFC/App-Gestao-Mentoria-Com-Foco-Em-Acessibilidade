package com.qxd.acessaedu

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.qxd.acessaedu.features.auth.presentation.LoginScreen
import com.qxd.acessaedu.ui.theme.AcessaEduTheme

@Composable
fun App() {
    AcessaEduTheme {
        Navigator(LoginScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}