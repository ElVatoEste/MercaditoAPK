package com.testapp.mercaditoapk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testapp.mercaditoapk.screen.CrearCuentaScreen
import com.testapp.mercaditoapk.screen.InicioScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "inicio") {
        composable("inicio") { InicioScreen(navController) }
        composable("crear_cuenta") { CrearCuentaScreen(navController) }
    }
}
