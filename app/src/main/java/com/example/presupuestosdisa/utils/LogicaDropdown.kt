package com.example.presupuestosdisa.utils

import com.example.presupuestosdisa.data.model.Producto

fun LogicaDropdown(productosList: MutableList<Producto>, tipoDropdown: String, nombreMenu: String, item: String) {
    var productoEncontrado = false
    productosList.forEach { producto ->
        if (producto.nombre == nombreMenu) {
            when (tipoDropdown) {
                "Tipo" -> producto.tipo = item
                "Serie" -> producto.tipoSerie = item
                "Color" -> producto.tipoColor = item
            }
            productoEncontrado = true
            return@forEach
        }
    }
    if (!productoEncontrado) {
        productosList.add(
            Producto(
                nombre = nombreMenu,
                tipo = if (tipoDropdown == "Tipo") item else "",
                tipoSerie = if (tipoDropdown == "Serie") item else "",
                tipoColor = if (tipoDropdown == "Color") item else ""
            )
        )
    }
}