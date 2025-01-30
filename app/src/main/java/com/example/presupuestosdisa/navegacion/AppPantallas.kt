package com.example.presupuestosdisa.navegacion

sealed class AppPantallas(val ruta: String) {
    object SplashScreen: AppPantallas("pantalla_splash")
    object PantallaPrincipal: AppPantallas("pantalla_principal")
    object PantallaPresupuesto: AppPantallas("pantalla_presupuesto")
}