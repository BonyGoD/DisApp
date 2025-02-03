package com.example.presupuestosdisa.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.presupuestosdisa.model.Producto

class SharedViewModel: ViewModel() {

    private val listaProductos = mutableStateOf<List<Producto>>(emptyList())
    val productos: State<List<Producto>> = listaProductos

    fun agregarProducto(producto: Producto) {
        listaProductos.value = listaProductos.value + producto
    }
}