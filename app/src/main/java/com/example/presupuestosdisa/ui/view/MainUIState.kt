package com.example.presupuestosdisa.ui.view

import com.example.presupuestosdisa.data.model.ProductoInfo

sealed class MainUIState {
    object Loading: MainUIState()
    data class Success(val data: List<ProductoInfo>): MainUIState()
    data class Error(val error: String): MainUIState()
}