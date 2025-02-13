package com.example.presupuestosdisa.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.presupuestosdisa.data.model.Producto
import com.example.presupuestosdisa.utils.LogicaDropdown
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {

    private val listaProductos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = listaProductos

    private val logicaDropdown = LogicaDropdown()

    fun agregarListaProductos() {
        listaProductos.value = logicaDropdown.getProductList()
    }
}