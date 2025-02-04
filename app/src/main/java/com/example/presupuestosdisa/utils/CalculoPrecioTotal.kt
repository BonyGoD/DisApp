package com.example.presupuestosdisa.utils

import android.annotation.SuppressLint
import com.example.presupuestosdisa.model.Producto
import com.example.presupuestosdisa.viewModel.ProductoViewModel

fun calcularPrecioTotal(productos: List<Producto>?, producto: Producto?): String {

    var precio = 0.0
    val productoViewModel = ProductoViewModel()

    if(productos != null) {
        productos.forEach { itemProducto ->
           precio += calcularPrecio(itemProducto, productoViewModel)
        }
    } else {
       precio = calcularPrecio(producto, productoViewModel)
    }

    return String.format("%.2f", precio)
}

fun calcularPrecio(producto: Producto?, productoViewModel: ProductoViewModel): Double {

    var precioVentana = 0.0
    var metroLineal = 0.0
    var precioVidrio = 0.0
    var precioPersiana = 0.0
    var precioRegistro = 0.0
    var precioTotal = 0.0

        when(producto?.nombre) {
            "Ventana" -> {
                producto.alto.let { alto ->
                    producto.ancho.let { ancho ->
                        metroLineal += ((alto * 0.001) + (ancho * 0.001)) * 2
                    }
                }

                if (producto.tipoSerie == "RPT"){
                    precioVentana += (
                            productoViewModel.tipoSerie.value.find { it.nombre == producto.tipoSerie }?.precio ?: 0
                            ) * metroLineal
                } else if (producto.tipoSerie == "Fria"){
                    precioVentana += (
                            productoViewModel.tipoSerie.value.find { it.nombre == producto.tipoSerie }?.precio ?: 0
                            ) * metroLineal
                }

                if (producto.tipoColor == "Ral estandar") {
                    val precioColor = productoViewModel.listaColores.value.find { it.nombre == producto.tipoColor }?.precio ?: 0.0
                    precioVentana = precioVentana + (precioVentana * precioColor)
                } else if (producto.tipoColor == "Imitacion madera") {
                    val precioColor = productoViewModel.listaColores.value.find { it.nombre == producto.tipoColor }?.precio ?: 0.0
                    precioVentana = precioVentana + (precioVentana * precioColor)
                }

                if (producto.oscilobatiente) {
                    precioVentana += 100.0
                }

                precioTotal += precioVentana

            }
            "Vidrio" -> {

                producto.alto.let { alto ->
                    producto.ancho.let { ancho ->
                        metroLineal += (alto * 0.001) * (ancho * 0.001)
                    }
                }

                precioVidrio += (productoViewModel.listaTipoVidrio.value.find { it.tipo == producto.tipo }?.precio ?: 0) * (metroLineal * 2)

                precioTotal += precioVidrio
            }
            "Persiana" -> {
                producto.alto.let { alto ->
                    producto.ancho.let { ancho ->
                        metroLineal += (alto * 0.001) * (ancho * 0.001)
                    }
                }
                when(producto.tipo) {
                    "R45" -> {
                        val precioColor = productoViewModel.listaColores.value.find { it.nombre == producto.tipoColor }?.precio ?: 0.0
                        precioPersiana = (productoViewModel.listaTipoPersiana.value.find { it.tipo == producto.tipo }?.precio ?: 0) +
                                (precioPersiana * precioColor)
                    }
                    "C45" -> {
                        val precioColor = productoViewModel.listaColores.value.find { it.nombre == producto.tipoColor }?.precio ?: 0.0
                        precioPersiana = (productoViewModel.listaTipoPersiana.value.find { it.tipo == producto.tipo }?.precio ?: 0) +
                                (precioPersiana * precioColor)
                    }
                    "Monoblock" -> {
                        val precioColor = productoViewModel.listaColores.value.find { it.nombre == producto.tipoColor }?.precio ?: 0.0
                        precioPersiana = (productoViewModel.listaTipoPersiana.value.find { it.tipo == producto.tipo }?.precio ?: 0) +
                                (precioPersiana * precioColor)
                    }
                }
                precioTotal += precioPersiana
            }
            "Registro" -> {
                precioRegistro = (productoViewModel.listaTipoRegistro.value.find { it.tipo == producto.tipo }?.precio ?: 0) * (producto.ancho * 0.001)

                precioTotal += precioRegistro
            }
        }
    return precioTotal
}