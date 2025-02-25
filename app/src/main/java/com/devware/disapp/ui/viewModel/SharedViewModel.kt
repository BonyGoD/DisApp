package com.devware.disapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.devware.disapp.data.model.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
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