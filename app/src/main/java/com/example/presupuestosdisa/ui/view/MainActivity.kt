package com.example.presupuestosdisa.ui.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presupuestosdisa.navegacion.AppNavegacion
import com.example.presupuestosdisa.ui.viewModel.ProductoMenuViewModel

class MainActivity : AppCompatActivity() {

    private val productoMenuViewModel: ProductoMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productoMenuViewModel.onCreate()

        setContent {
            AppNavegacion(productoMenuViewModel)
        }
    }
}