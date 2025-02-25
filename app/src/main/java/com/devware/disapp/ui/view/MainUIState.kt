package com.devware.disapp.ui.view

import com.devware.disapp.data.model.ProductoInfo

sealed class MainUIState {
    object Loading: MainUIState()
    data class Success(val data: List<ProductoInfo>): MainUIState()
    data class Error(val error: String): MainUIState()
}