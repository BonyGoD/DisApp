package com.example.presupuestosdisa.navegacion

sealed class AppPantallas(val ruta: String) {
    object PantallaPrincipal: AppPantallas("pantalla_principal")
    object PantallaPresupuesto: AppPantallas("pantalla_presupuesto")
}