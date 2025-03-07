package com.devware.disapp.data.network

import com.devware.disapp.data.model.Colores
import com.devware.disapp.data.model.ProductoInfo
import com.devware.disapp.data.model.Serie
import com.devware.disapp.data.model.Tipo
import com.devware.disapp.data.repositories.AccesoAppRepository.Companion.MIN_VERSION
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
        val productoMenu: MutableList<ProductoInfo> = mutableListOf()
        val productos = firebase.collection("productos").get().await()

        productos.documents.forEach { productoDoc ->
            val producto = ProductoInfo(
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
        }

        remoteConfig.fetchAndActivate().await()
        val minVersion = remoteConfig.getString(MIN_VERSION)
        return if (minVersion.isBlank()) listOf(0, 0, 0)
        else minVersion.split(".").map { it.toInt() }
    }
}