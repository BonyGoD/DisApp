package com.example.presupuestosdisa.ui.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presupuestosdisa.navegacion.AppNavegacion
import com.example.presupuestosdisa.ui.viewModel.FireBaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val fireBaseViewModel: FireBaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fireBaseViewModel.getProductos()

        setContent {
            AppNavegacion(fireBaseViewModel)
        }
    }
}