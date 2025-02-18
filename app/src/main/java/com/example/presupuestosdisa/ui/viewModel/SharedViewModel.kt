package com.example.presupuestosdisa.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.presupuestosdisa.data.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    fun agregarListaProductos(nuevosProductos: List<Producto>) {
        val listaActualizada = _productos.value.toMutableList()
        listaActualizada.addAll(nuevosProductos)
        _productos.value = listaActualizada
    }

    fun eliminarProducto(producto: Producto) {
        val lista = _productos.value.toMutableList()
        lista.remove(producto)
        _productos.value = lista
    }
}