package com.testapp.mercaditoapk

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.testapp.mercaditoapk.screen.CrearCuentaScreen
import com.testapp.mercaditoapk.screen.InicioScreen
import com.testapp.mercaditoapk.screen.LoginScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "inicio",
        modifier = modifier
    ) {
        composable("inicio") {
            InicioScreen(navController)
        }
        composable("crear_cuenta") {
            CrearCuentaScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
    }
}
