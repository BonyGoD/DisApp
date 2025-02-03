package com.example.presupuestosdisa.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presupuestosdisa.pantallas.PantallaPresupuesto
import com.example.presupuestosdisa.pantallas.PantallaPrincipal
import com.example.presupuestosdisa.pantallas.SplashScreen
import com.example.presupuestosdisa.viewModel.SharedViewModel

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable<SplashScreen> {
            SplashScreen {
                navController.navigate(PantallaPrincipal)
            }
        }

        composable<PantallaPrincipal> {
            PantallaPrincipal(sharedViewModel) {
                navController.navigate(PantallaPresupuesto)
            }
        }

        composable<PantallaPresupuesto> {
            PantallaPresupuesto(sharedViewModel) {
                navController.popBackStack()
            }
        }
    }
}
/*        composable(route = AppPantallas.SplashScreen.ruta) {
            SplashScreen(navController)
        }
        composable(route = AppPantallas.PantallaPrincipal.ruta) {
            PantallaPrincipal(navController, null)
        }
        composable(route = AppPantallas.PantallaPrincipalConProductos.ruta + "/{productosJson}",
            arguments = listOf(navArgument("productosJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val productosJson = backStackEntry.arguments?.getString("productosJson")
            val productosList = Gson().fromJson(productosJson, Array<Producto>::class.java).toList()
            PantallaPrincipal(navController, productosList)
        }
        composable(route = AppPantallas.PantallaPresupuesto.ruta) {
            PantallaPresupuesto(navController)
        }*/