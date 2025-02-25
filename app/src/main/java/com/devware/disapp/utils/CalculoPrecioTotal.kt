package com.devware.disapp.utils

import android.annotation.SuppressLint
import com.devware.disapp.data.model.Producto
import com.devware.disapp.ui.viewModel.FireBaseViewModel

@SuppressLint("DefaultLocale")
fun calcularPrecioTotal(
    productos: List<Producto>?,
    producto: Producto?,
    fireBaseViewModel: FireBaseViewModel
): String {

    var precio = 0.0

    if (productos != null) {
        productos.forEach { itemProducto ->
            precio += calcularPrecio(itemProducto, fireBaseViewModel)
        }
    } else {
        precio = calcularPrecio(producto, fireBaseViewModel)
    }

    return String.format("%.2f€", precio)
}

fun calcularPrecio(producto: Producto?, fireBaseViewModel: FireBaseViewModel): Double {

    var precioVentana: Double? = 0.0
    var metroLineal = 0.0
    var precioVidrio = 0.0
    var precioPersiana = 0.0
    var precioRegistro = 0.0
    var precioTotal = 0.0
    var precioColor: Double? = 0.0

    when (producto?.nombre) {
        "Ventana" -> {
            producto.alto.let { alto ->
                producto.ancho.let { ancho ->
                    metroLineal += ((alto * 0.001) + (ancho * 0.001)) * 2
                }
            }

            fireBaseViewModel.producto.value?.forEach { prod ->
                prod.tipo?.forEach { tipo ->
                    tipo.serie?.forEach {
                        if (it.nombre == producto.tipoSerie) {
                            precioVentana = it.precio
                        }
                    }
                }
            }
            precioVentana = precioVentana!! * metroLineal

            if (producto.tipoColor != "Blanco") {
                fireBaseViewModel.producto.value?.forEach { prod ->
                    prod.colores?.forEach {
                        if (it.nombre == producto.tipoColor) {
                            precioColor = it.precio
                        }
                    }
                }

                precioVentana = precioVentana!! + (precioVentana!! * precioColor!!)
                precioVentana = precioVentana!! + 0.0
            }

            if (producto.oscilobatiente) {
                precioVentana = precioVentana!! + 100.0
            }
            precioTotal += precioVentana!!
        }

        "Vidrio" -> {
            producto.alto.let { alto ->
                producto.ancho.let { ancho ->
                    metroLineal += ((alto * 0.001) * (ancho * 0.001)) * 2
                }
            }

            fireBaseViewModel.producto.value?.forEach { prod ->
                prod.tipo?.forEach {
                    if (it.tipo == producto.tipo) {
                        precioVidrio = it.precio?.toDouble() ?: 0.0
                    }
                }
            }
            precioTotal += precioVidrio * metroLineal
        }

        "Persiana" -> {
            producto.alto.let { alto ->
                producto.ancho.let { ancho ->
                    metroLineal += (alto * 0.001) * (ancho * 0.001)
                }
            }
            fireBaseViewModel.producto.value?.forEach { prod ->
                val tipoP = prod.tipo?.find { it.tipo == producto.tipo }
                prod.colores?.forEach { color ->
                    if (color.nombre == producto.tipoColor && tipoP?.tipo == producto.tipo) {
                        precioColor = color.precio
                        precioPersiana = tipoP.precio?.toDouble() ?: 0.0
                        if (precioColor != 0.0) {
                            precioPersiana += (precioPersiana * precioColor!!)
                        }
                    }
                }
            }

            precioTotal += precioPersiana * metroLineal

            if (producto.motorizada) {
                precioTotal += 100.0
            }
        }

        "Registro" -> {
            fireBaseViewModel.producto.value?.forEach { prod ->
                prod.tipo?.forEach { tipo ->
                    if (tipo.tipo == producto.tipo) {
                        precioRegistro = (tipo.precio?.toDouble() ?: 0.0) * (producto.ancho * 0.001)
                    }
                }
            }
            precioTotal += precioRegistro
        }
    }
    return precioTotal
}