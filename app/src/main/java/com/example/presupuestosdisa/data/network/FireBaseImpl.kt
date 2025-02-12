package com.example.presupuestosdisa.data.network

import com.example.presupuestosdisa.data.model.Colores
import com.example.presupuestosdisa.data.model.ProductoInfo
import com.example.presupuestosdisa.data.model.Serie
import com.example.presupuestosdisa.data.model.Tipo
import com.example.presupuestosdisa.data.repositories.AccesoAppRepository.Companion.MIN_VERSION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseClientImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : FirebaseClient
{

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

    override suspend fun getMinVersion(): List<Int> {

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
            fetchAndActivate()
        }

        remoteConfig.fetch(0)
        remoteConfig.activate().await()
        val minVersion = remoteConfig.getString(MIN_VERSION)
        return if(minVersion.isBlank()) return listOf(0, 0, 0)
        else minVersion.split(".").map { it.toInt() }
    }
}