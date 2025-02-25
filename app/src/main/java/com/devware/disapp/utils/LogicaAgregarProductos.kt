package com.devware.disapp.utils

import androidx.compose.runtime.MutableState
import com.devware.disapp.data.model.Producto
import com.devware.disapp.utils.LogicaAgregarProductos.ProductoManager.productosList

class LogicaAgregarProductos {

    object ProductoManager {
        val productosList: MutableList<Producto> = mutableListOf()
    }

    fun logicaDropdown(tipoDropdown: String, nombreMenu: String, item: String) {
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

    fun logicaCheckBox(nombreMenu: String, nombre: String, isChecked: Boolean) {
        var productoEncontrado = false
        productosList.forEach { producto ->
            if (producto.nombre == nombreMenu) {
                when (nombre) {
                    "Motorizada" -> producto.motorizada = isChecked
                    "Oscilobatiente" -> producto.oscilobatiente = isChecked
                }
                productoEncontrado = true
                return@forEach
            }
        }
        if (!productoEncontrado) {
            productosList.toMutableList().add(
                Producto(
                    nombre = nombreMenu,
                    motorizada = if (nombre == "Motorizada") isChecked else false,
                    oscilobatiente = if (nombre == "Oscilobatiente") isChecked else false

                )
            )
        }
    }

    fun logicaTextFields(nombreMenu: String, tipoMedida: String, medida: MutableState<String>, it: String) {

        val maxDigits = 5

        if (it.length <= maxDigits && it.all { char -> char.isDigit() }) {
            medida.value = it
            var productoEncontrado = false
            LogicaAgregarProductos().getProductList().forEach { producto ->
                if (producto.nombre == nombreMenu) {
                    when (tipoMedida) {
                        "Ancho" -> producto.ancho = if (it.isNotEmpty()) it.toLong() else 0L
                        "Alto" -> producto.alto = if (it.isNotEmpty()) it.toLong() else 0L
                    }
                    productoEncontrado = true
                    return@forEach
                }
            }
            if (!productoEncontrado) {
                LogicaAgregarProductos().getProductList().toMutableList().add(
                    Producto(
                        nombre = nombreMenu,
                        ancho = if (tipoMedida == "Ancho") it.toLong() else 0,
                        alto = if (tipoMedida == "Alto") it.toLong() else 0
                    )
                )
            }
        }
    }

    fun getProductList(): List<Producto> {
        return productosList
    }

    fun eliminarProductos() {
        productosList.clear()
    }
}
