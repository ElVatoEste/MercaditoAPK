package com.testapp.mercaditoapk

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.testapp.mercaditoapk.screen.CrearCuentaScreen
import com.testapp.mercaditoapk.screen.CrearPublicacionScreen
import com.testapp.mercaditoapk.screen.DetailScreen
import com.testapp.mercaditoapk.screen.InicioScreen
import com.testapp.mercaditoapk.screen.LoginScreen
import com.testapp.mercaditoapk.screen.MenuScreen
import com.testapp.mercaditoapk.screen.RegistrarScreen
import com.testapp.mercaditoapk.screen.TestScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
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
        composable("test") {
            TestScreen(navController)
        }
        composable("crear_cuenta") {
            CrearCuentaScreen(navController)
        }
        composable("publicacionaadd"){
            CrearPublicacionScreen(navController)
        }
        composable(
            route = "registrar/{cif}/{nombres}/{apellidos}/{correo}/{contrasena}",
            arguments = listOf(
                navArgument("cif") { type = NavType.StringType },
                navArgument("nombres") { type = NavType.StringType },
                navArgument("apellidos") { type = NavType.StringType },
                navArgument("correo") { type = NavType.StringType },
                navArgument("contrasena") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            RegistrarScreen(
                navController = navController,
                cif = backStackEntry.arguments?.getString("cif") ?: "",
                nombres = backStackEntry.arguments?.getString("nombres") ?: "",
                apellidos = backStackEntry.arguments?.getString("apellidos") ?: "",
                correo = backStackEntry.arguments?.getString("correo") ?: "",
                contrasena = backStackEntry.arguments?.getString("contrasena") ?: ""
            )
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable(
            route = "menu/{cif}",
            arguments = listOf(
                navArgument("cif") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            MenuScreen(
                navController = navController,
                cif = backStackEntry.arguments?.getString("cif") ?: ""
            )
        }
        composable(
            route = "detail_screen/{publicationId}",
            arguments = listOf(
                navArgument("publicationId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val publicationId = backStackEntry.arguments?.getLong("publicationId") ?: -1
            DetailScreen(navController = navController, publicationId = publicationId)
        }
    }
}
