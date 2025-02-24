package com.example.presupuestosdisa.ui.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presupuestosdisa.navegacion.AppNavegacion
import com.example.presupuestosdisa.ui.theme.PresupuestosDisaTheme
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val fireBaseViewModel: FireBaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        var dataLoaded = false

        splashScreen.setKeepOnScreenCondition {
            !dataLoaded
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fireBaseViewModel.uiState.collect { isDataLoaded ->
                    when (isDataLoaded) {
                        MainUIState.Loading -> {
                            dataLoaded = false
                        }
                        is MainUIState.Success -> {
                            dataLoaded = true
                        }
                        is MainUIState.Error -> {
                            dataLoaded = false
                        }
                    }
                }
            }
        }

        setContent {
            PresupuestosDisaTheme {
                AppNavegacion()
            }
        }
    }
}