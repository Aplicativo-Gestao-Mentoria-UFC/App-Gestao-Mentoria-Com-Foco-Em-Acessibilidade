package com.qxd.acessaedu

import androidx.compose.runtime.Composable
import com.qxd.acessaedu.core.navigation.AppNavHost
import com.qxd.acessaedu.ui.theme.AcessaEduTheme

@Composable
fun App() {

    AcessaEduTheme {
        AppNavHost()
    }
}