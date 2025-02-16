package com.example.presupuestosdisa.ui.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.presupuestosdisa.navegacion.AppNavegacion
import com.example.presupuestosdisa.ui.theme.PresupuestosDisaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            PresupuestosDisaTheme {
                AppNavegacion()
            }
        }
    }
}