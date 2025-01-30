package com.example.presupuestosdisa.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presupuestosdisa.pantallas.PantallaPresupuesto
import com.example.presupuestosdisa.pantallas.PantallaPrincipal
import com.example.presupuestosdisa.pantallas.SplashScreen

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppPantallas.SplashScreen.ruta) {
        composable(route = AppPantallas.SplashScreen.ruta) {
            SplashScreen(navController)
        }
        composable(route = AppPantallas.PantallaPrincipal.ruta) {
            PantallaPrincipal(navController)
        }
        composable(route = AppPantallas.PantallaPresupuesto.ruta) {
            PantallaPresupuesto(navController)
        }
    }
}