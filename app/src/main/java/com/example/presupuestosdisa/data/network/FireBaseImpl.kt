package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.data.model.Colores
import com.example.presupuestosdisa.data.model.ProductoInfo
import com.example.presupuestosdisa.data.model.Serie
import com.example.presupuestosdisa.data.model.Tipo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseClientImpl(private val firebase: FirebaseFirestore) : FirebaseClient {

    override suspend fun getProductos(): List<ProductoInfo> {

        var productoMenu: MutableList<ProductoInfo> = mutableListOf()

        val productos = firebase.collection("productos").get().await()

        var producto: ProductoInfo


        productos.documents.forEach { productoDoc ->
            producto = ProductoInfo(
                nombre = productoDoc.getString("nombre"),
                motorizada = productoDoc.getBoolean("motorizada")
            )

            // Asignar coleccion simple a propiedad
            val colores = productoDoc.reference.collection("colores").get().await()
            producto.colores = colores.toObjects(Colores::class.java)

            val tipo = productoDoc.reference.collection("tipo").get().await()
            producto.tipo = tipo.toObjects(Tipo::class.java)

            // Asignar coleccion con subcolecciones a propiedad
            tipo.forEach { tipoDoc ->
                val serie = tipoDoc.reference.collection("serie").get().await()

                serie.documents.forEach { serieDoc ->
                    producto.tipo?.forEach { tipo ->
                        if (tipo.tipo != "Elevable") {
                            tipo.serie = serie.toObjects(Serie::class.java)
                        }
                    }
                }
            }
            productoMenu.add(producto)
        }
        return productoMenu
    }
}