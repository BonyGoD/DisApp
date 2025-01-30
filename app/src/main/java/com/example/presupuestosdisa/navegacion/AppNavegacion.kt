package com.example.presupuestosdisa.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presupuestosdisa.screens.PantallaPresupuesto
import com.example.presupuestosdisa.screens.PantallaPrincipal

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppPantallas.PantallaPrincipal.ruta) {
        composable(route = AppPantallas.PantallaPrincipal.ruta) {
            PantallaPrincipal(navController)
        }
        composable(route = AppPantallas.PantallaPresupuesto.ruta) {
            PantallaPresupuesto(navController)
        }
    }
}