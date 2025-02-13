package com.example.presupuestosdisa.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presupuestosdisa.ui.view.PantallaPresupuesto
import com.example.presupuestosdisa.ui.view.PantallaPrincipal
import com.example.presupuestosdisa.ui.view.SplashScreen
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import com.example.presupuestosdisa.ui.viewModel.SharedViewModel

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val fireBaseViewModel: FireBaseViewModel = hiltViewModel()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable<SplashScreen> {
            SplashScreen(fireBaseViewModel, ) {
                navController.navigate(PantallaPrincipal){
                    popUpTo(SplashScreen) { inclusive = true }
                }
            }
        }

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