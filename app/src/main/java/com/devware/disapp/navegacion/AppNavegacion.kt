package com.devware.disapp.navegacion

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devware.disapp.ui.view.PantallaPresupuesto
import com.devware.disapp.ui.view.PantallaPrincipal
import com.devware.disapp.ui.viewModel.FireBaseViewModel
import com.devware.disapp.ui.viewModel.SharedViewModel

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val fireBaseViewModel: FireBaseViewModel = hiltViewModel()
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = PantallaPrincipal) {

        composable<PantallaPrincipal> {
            PantallaPrincipal(fireBaseViewModel, sharedViewModel) {
                navController.navigate(PantallaPresupuesto)
            }
        }

        composable<PantallaPresupuesto> {
            PantallaPresupuesto(fireBaseViewModel, sharedViewModel) {
                navController.navigateUp()
            }
        }
    }
}